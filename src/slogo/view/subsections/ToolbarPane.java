package slogo.view.subsections;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import slogo.logicalcontroller.LogicalController;
import slogo.view.windows.SlogoView;

public class ToolbarPane implements SubPane {

  private SlogoView myViewer;

  private Button myLoader = new Button("Load File");
  private Button myLoadAndRun = new Button("Load & Run");
  private Button myBGColor = new Button("Background Color");
  private Button myTurtleImage = new Button("Turtle Image");
  private Button myPenColor = new Button("Pen Color");
  private Button myClearScreen = new Button("Clear Screen");
  private Button myLanguage = new Button("Language");
  private Button myHelpInfo = new Button("Help/Info");

  public ToolbarPane(SlogoView viewer){
    myViewer = viewer;
  }

  @Override
  public ToolBar getNode() {
    
    initializeButtons();

    return new ToolBar(
      myLoader,
      myLoadAndRun,
      new Separator(),
      myBGColor,
      myTurtleImage,
      myPenColor,
      myClearScreen,
      new Separator(),
      myLanguage,
      myHelpInfo);
  }

  private void initializeButtons() {
    myLoader.setOnAction(e -> loadFile());
    myLoadAndRun.setOnAction(e -> loadAndRun());
//    myBGColor.setOnAction();
//    myTurtleImage.setOnAction();
//    myPenColor.setOnAction();
//    myClearScreen.setOnAction();
//    myLanguage.setOnAction();
//    myHelpInfo.setOnAction();
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
      LogicalController.handleNewCommand(fileContents);
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
