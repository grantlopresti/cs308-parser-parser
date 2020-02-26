package slogo.view.subsections;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import slogo.logicalcontroller.LogicalController;
import slogo.view.windows.SlogoView;

import javax.script.ScriptException;

public class ToolbarPane implements SubPane {

  private static final String DEFAULT_LANGUAGE = "English";

  private SlogoView myViewer;

  private Button myLoader = new Button("Load File");
  private Button myLoadAndRun = new Button("Load & Run");
  private ColorPicker myBGColorPicker = new ColorPicker();
  private Button myTurtleImage = new Button("Turtle Image");
  private Button myPenColor = new Button("Pen Color");
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

  public ToolbarPane(SlogoView viewer){
    myViewer = viewer;
  }

  @Override
  public ToolBar getNode() throws IOException {
    
    initializeButtons();

    return new ToolBar(
      myLoader,
      myLoadAndRun,
      new Separator(),
      new Text("BG Color:"),
      myBGColorPicker,
      myTurtleImage,
      myPenColor,
      myClearScreen,
      new Separator(),
      myLanguage,
      myHelpInfo);
  }

  private void initializeButtons() throws IOException {
    myLoader.setOnAction(e -> loadFile());
    myLoadAndRun.setOnAction(e -> {
      try {
        loadAndRun();
      } catch (ScriptException ex) {
        ex.printStackTrace();
      }
    });
//    myBGColor.setOnAction();
    myLoadAndRun.setOnAction(e -> {
      try {
        loadAndRun();
      } catch (ScriptException ex) {
        ex.printStackTrace();
      }
    });
    myBGColorPicker.setOnAction(t -> {
      Color c = myBGColorPicker.getValue();
      myViewer.setBGColor(c.getRed(), c.getGreen(), c.getBlue());
    });
//    myTurtleImage.setOnAction();
//    myPenColor.setOnAction();
    myClearScreen.setOnAction(e -> clearVisualizationScreen());
    setDefaultLanguage();
    myLanguage.getSelectionModel().selectedItemProperty().addListener( (options, oldValue,
        newValue) -> {
      changeLanguage(newValue);
    });
//    myHelpInfo.setOnAction();
  }

  private void clearVisualizationScreen() {
    myViewer.clearScreen();
  }

  private void setDefaultLanguage() throws IOException {
    myLanguage.setValue(DEFAULT_LANGUAGE);
    changeLanguage(DEFAULT_LANGUAGE);
  }

  private void loadFile() {
    File file = getUserFile();
    String fileContents = getTextFromFile(file);
    myViewer.setUserInputAreaText(fileContents);
  }

  private void loadAndRun() throws ScriptException {
    File file = getUserFile();
    try {
      sendCommands(file);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
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

  private void sendCommands(File file) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, IOException, ScriptException {
      String fileContents = getTextFromFile(file);
    try {
      LogicalController.handleNewCommand(fileContents);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    }
  }

  private void changeLanguage(String language) {
    try {
      LogicalController.setLanguage(language);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private String getTextFromFile(File file) {
    Path filePath = file.toPath();
    try {
      return new String(Files.readAllBytes(filePath));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

}
