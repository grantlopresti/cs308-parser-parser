package slogo.view.subsections;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.script.ScriptException;

import slogo.exceptions.InvalidCommandException;
import slogo.exceptions.InvalidCommandFileException;
import slogo.exceptions.InvalidLanguageException;
import slogo.logicalcontroller.LogicalController;
import slogo.view.TurtleImage;
import slogo.view.windows.SlogoInterface;
import slogo.visualcontroller.VisualError;

import javax.script.ScriptException;

public class ToolbarPane extends ToolBar {

  private LogicalController myLogicalController;
  private static final String DEFAULT_LANGUAGE = "English";
  private static final String DEFAULT_TURTLE_IMAGE = "Turtle";

  private static final String myButtonProperties = "properties.buttons";
  private ResourceBundle myButtonResources;

  private SlogoInterface myViewer;

  private Button myLoader = new Button("Load File");
  private Button myLoadAndRun = new Button("Load & Run");
  private ColorPicker myBGColorPicker = new ColorPicker();
  private ComboBox<String> myTurtleImage = new ComboBox<>();
  private ColorPicker myPenColorPicker = new ColorPicker();
  private Button myClearScreen = new Button("Clear Screen");
  private static final ObservableList<String> languageOptions =
      FXCollections.observableArrayList(
          "English",
          "Chinese",
          "French",
          "German",
          "Italian",
          "Portuguese",
          "Russian",
          "Spanish",
          "Urdu"
      );
  private ComboBox<String> myLanguage = new ComboBox<>(languageOptions);
  private Button myHelpInfo = new Button("Help/Info");

  public ToolbarPane(SlogoInterface viewer, LogicalController logicalController) {
    myViewer = viewer;
    myLogicalController = logicalController;
    for (TurtleImage value : TurtleImage.values()){
      myTurtleImage.getItems().add(value.getName());
    }
    myButtonResources = ResourceBundle.getBundle(myButtonProperties);
  }

  public ToolBar getNode() {

    initializeButtons();

    return new ToolBar(
        myLoader,
        myLoadAndRun,
        new Separator(),
        new Text("BG Color:"),
        myBGColorPicker,
        new Separator(),
        new Text("Turtle Image:"),
        myTurtleImage,
        new Separator(),
        new Text("Pen Color:"),
        myPenColorPicker,
        new Separator(),
        myClearScreen,
        new Separator(),
        new Text("Language:"),
        myLanguage,
        new Separator(),
        myHelpInfo);
  }

  private void initializeButtons() {
    myLoader.setOnAction(e -> loadFile());
    initializeLoadAndRunButton();
    myBGColorPicker.setOnAction(t -> {
      Color c = myBGColorPicker.getValue();
      myViewer.setBGColor(c.getRed(), c.getGreen(), c.getBlue());
    });
    setDefaultTurtleImage();
    myTurtleImage.getSelectionModel().selectedItemProperty().addListener((options, oldValue,
        newValue) -> myViewer.changeTurtleImage(newValue));
    myPenColorPicker.setOnAction(t -> {
      Color c = myPenColorPicker.getValue();
      myViewer.setPenColor(c.getRed(), c.getGreen(), c.getBlue());
    });
    myClearScreen.setOnAction(e -> clearVisualizationScreen());
    setDefaultLanguage();
    myLanguage.getSelectionModel().selectedItemProperty().addListener((options, oldValue,
        newValue) -> changeLanguage(newValue));
    myHelpInfo.setOnAction(e -> showHelpWindow());
  }

  private void setDefaultTurtleImage() {
    myTurtleImage.setValue(DEFAULT_TURTLE_IMAGE);
    myViewer.changeTurtleImage(DEFAULT_TURTLE_IMAGE.toUpperCase());
  }

  private void initializeLoadAndRunButton() {
    myLoadAndRun.setOnAction(e -> {
      try {
        loadAndRun();
      } catch (NoSuchMethodException | InstantiationException | ScriptException | IllegalAccessException | InvocationTargetException | ClassNotFoundException ex) {
        ex.printStackTrace();
      }
    });
  }


  private void showHelpWindow() {
    Stage stage = new Stage();
    stage.setTitle("Slogo Help/Info");
    stage.setWidth(1200);
    stage.setHeight(600);
    Scene scene = new Scene(new Group());
    VBox root = new VBox();
    final WebView browser = new WebView();
    final WebEngine webEngine = browser.getEngine();
    webEngine
        .load("https://www2.cs.duke.edu/courses/compsci308/current/assign/03_parser/commands.php");

    root.getChildren().addAll(browser);
    scene.setRoot(root);

    stage.setScene(scene);
    stage.show();
  }

  private void clearVisualizationScreen() {
    myViewer.clearScreen();
  }

  private void setDefaultLanguage() {
    myLanguage.setValue(DEFAULT_LANGUAGE);
    changeLanguage(DEFAULT_LANGUAGE);
  }

  private void loadFile() {
    File file = getUserFile();
    String fileContents = getTextFromFile(file);
    myViewer.setUserInputAreaText(fileContents);
  }

  private void loadAndRun() throws NoSuchMethodException, InstantiationException, ScriptException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
    File file = getUserFile();
    sendCommands(file);
  }

  private File getUserFile() {
    FileChooser fc = new FileChooser();
    String dataPath = System.getProperty("user.dir") + "/data/examples";
    File workingDirectory = new File(dataPath);
    fc.setInitialDirectory(workingDirectory);

    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Logo files (*.logo)",
        "*.logo");
    fc.getExtensionFilters().add(extFilter);

    return fc.showOpenDialog(new Stage());
  }

  private void sendCommands(File file) throws NoSuchMethodException, InstantiationException, ScriptException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
    String fileContents = getTextFromFile(file);
    try {
      assert fileContents != null;
      myLogicalController.handleNewCommand(fileContents);
    } catch (Exception e) {
      myViewer.announceError(new VisualError(new InvalidCommandException("The "
          + "following command is invald: \n" + fileContents)));
    }
  }

  private void changeLanguage(String language) {
    try {
      myLogicalController.setLanguage(language);
    } catch (IOException e) {
      myViewer.announceError(new VisualError(new InvalidLanguageException("The chosen language: " + language
          + " is invalid. \n Please try again!")));
    }
  }

  private String getTextFromFile(File file) {
    Path filePath = file.toPath();
    try {
      return new String(Files.readAllBytes(filePath));
    } catch (IOException e) {
      myViewer.announceError(new VisualError(new InvalidCommandFileException("The file path: " + file + " "
          + "is invalid. \n Please try again!")));
    }
    return null;
  }

}
