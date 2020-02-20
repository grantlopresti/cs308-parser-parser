package slogo.view.subsections;

import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import slogo.visualcontroller.VisualController;

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
    myLoader.setOnAction(e -> VisualController.loadFile());
//    myLoadAndRun.setOnAction();
//    myBGColor.setOnAction();
//    myTurtleImage.setOnAction();
//    myPenColor.setOnAction();
//    myClearScreen.setOnAction();
//    myLanguage.setOnAction();
//    myHelpInfo.setOnAction();
  }
}
