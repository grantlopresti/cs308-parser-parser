package slogo.view.subsections;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import slogo.exceptions.InvalidCommandException;
import slogo.exceptions.InvalidCommandFileException;
import slogo.logicalcontroller.LogicalController;
import slogo.view.windows.SlogoView;
import slogo.visualcontroller.VisualError;

public class ToolbarPane extends ToolBar {

  private static final String DEFAULT_LANGUAGE = "English";

  private SlogoView myViewer;

  private Button myLoader = new Button("Load File");
  private Button myLoadAndRun = new Button("Load & Run");
  private ColorPicker myBGColorPicker = new ColorPicker();
  private Button myClearScreen = new Button("Clear Screen");
  private Button myDarkModeToggle = new Button("Toggle Mode");

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
          "Urdu",
              "Gibberish"
      );
  private ComboBox<String> myLanguage = new ComboBox<>(languageOptions);
  private Button myHelpInfo = new Button("Help/Info");

  public ToolbarPane(SlogoView viewer) {
    myViewer = viewer;
  }

  public ToolBar getNode() {

    initializeButtons();

    return new ToolBar(
        myLoader,
        myLoadAndRun,
        new Separator(),
        new Label("BG Color:"),
        myBGColorPicker,
        new Separator(),
        myClearScreen,
        new Separator(),
        new Label("Language:"),
        myLanguage,
        new Separator(),
        myHelpInfo,
        new Separator(),
        new Label("Dark Mode"),
        myDarkModeToggle);
  }

  private void initializeButtons() {
    myLoader.setOnAction(e -> loadFile());
    myLoadAndRun.setOnAction(e -> loadAndRun());
    myBGColorPicker.setOnAction(t -> {
      Color c = myBGColorPicker.getValue();
      myViewer.setBGColor(c.getRed(), c.getGreen(), c.getBlue());
    });
    myClearScreen.setOnAction(e -> clearVisualizationScreen());
    setDefaultLanguage();
    myLanguage.getSelectionModel().selectedItemProperty().addListener((options, oldValue,
        newValue) -> changeLanguage(newValue));
    myHelpInfo.setOnAction(e -> showHelpWindow());
    myDarkModeToggle.setOnAction(e -> myViewer.toggleDarkMode());
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

  private void loadAndRun() {
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

  private void sendCommands(File file) {
    String fileContents = getTextFromFile(file);
    try {
      assert fileContents != null;
      myViewer.handleNewCommand(fileContents);
    } catch (Exception e) {
      myViewer.announceError(new VisualError(new InvalidCommandException("The "
          + "following command is invalid: \n" + fileContents)));
    }
  }

  private void changeLanguage(String language) {
    myViewer.setLanguage(language);
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
