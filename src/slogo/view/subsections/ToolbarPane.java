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

public class ToolbarPane implements SubPane {

  Button myLoader = new Button("Load File");
  Button myLoadAndRun = new Button("Load & Run");
  Button myBGColor = new Button("Background Color");
  Button myTurtleImage = new Button("Turtle Image");
  Button myPenColor = new Button("Pen Color");
  Button myClearScreen = new Button("Clear Screen");
  Button myLanguage = new Button("Language");
  Button myHelpInfo = new Button("Help/Info");

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
//    myLoadAndRun.setOnAction();
//    myBGColor.setOnAction();
//    myTurtleImage.setOnAction();
//    myPenColor.setOnAction();
//    myClearScreen.setOnAction();
//    myLanguage.setOnAction();
//    myHelpInfo.setOnAction();
  }

  private void loadFile() {
    FileChooser fc = new FileChooser();
    String dataPath = System.getProperty("user.dir") + "/data/examples";
    File workingDirectory = new File(dataPath);
    fc.setInitialDirectory(workingDirectory);

    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Logo files (*.logo)",
        "*.logo");
    fc.getExtensionFilters().add(extFilter);

    File file = fc.showOpenDialog(new Stage());
    if (file != null) {
      sendCommands(file);
    }
  }

  private void sendCommands(File file) {
    try {
      Path filePath = file.toPath();
      String fileContents = new String(Files.readAllBytes(filePath));
      LogicalController.handleNewCommand(fileContents);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }




}
