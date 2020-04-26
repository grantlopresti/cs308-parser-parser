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
import slogo.view.windows.SlogoView;
import slogo.visualcontroller.VisualError;

public class ToolbarPane extends ToolBar {

  private static final String DEFAULT_LANGUAGE = "English";
  public static final int STAGE_WIDTH = 1200;
  public static final int STAGE_HEIGHT = 600;

  private SlogoView myViewer;

  private Button myLoader = new Button("Load File");
  private Button myLoadAndRun = new Button("Load & Run");
  private ColorPicker myBGColorPicker = new ColorPicker();
  private Button myClearScreen = new Button("Clear Screen");
  private Button myDarkModeToggle = new Button("Toggle Mode");

  //SHOULD BE READ IN FROM A RESOURCE FILE
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

  /**
   * constructor, creates the toolbar pane and assigns it a SlogoView
   * @param viewer the SlogoView that owns it
   *
   * @author Grant LoPresti
   */
  public ToolbarPane(SlogoView viewer) {
    myViewer = viewer;
  }

  /**
   * creates the toolbar to be displayed at the top of the GUI (including associated buttons)
   * @return the ToolBar object
   */
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

  /**
   * Sets the specific actions for each button. I recognize that this should have been done using
   * a resource file and reflection, this has been done in my SubTab Factory and I sincerely
   * regret that I didn't have the time to implement this change here.
   *
   * @author Grant LoPresti
   */
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

  /**
   * Displays the "help window" as a direct link to the class website's help window
   *
   * @author Grant LoPresti
   */
  private void showHelpWindow() {
    Stage stage = new Stage();
    stage.setTitle("Slogo Help/Info");
    stage.setWidth(STAGE_WIDTH);
    stage.setHeight(STAGE_HEIGHT);
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

  /**
   * Opens a file chooser for the user to select a .logo file
   * @return the .logo file selected
   *
   * @author Grant LoPresti
   */
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

  /**
   * Reads commands from a file and send them to the user
   * @param file is the file the commands should be read from
   *
   * @author Grant LoPresti
   */
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

  /**
   * parses the input .logo file and returns the text based commands to be displayed or run
   * @param file the selected file containing the commands to be parsed
   * @return a string of all of the contained file text
   */
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
