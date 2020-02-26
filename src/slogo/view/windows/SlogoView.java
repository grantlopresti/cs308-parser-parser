package slogo.view.windows;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import slogo.logicalcontroller.LogicalController;
import slogo.logicalcontroller.command.Command;
import slogo.view.TurtleImage;
import slogo.view.subsections.*;
import slogo.visualcontroller.VisualCommand;
import slogo.visualcontroller.VisualData;
import slogo.visualcontroller.VisualLine;
import slogo.visualcontroller.VisualTurtle;
import slogo.visualcontroller.VisualUserFunction;

public class SlogoView extends Application {

  private static final int WINDOW_WIDTH = 1370;
  private static final int WINDOW_HEIGHT = 700;

  private static final int VISUALIZER_WIDTH = 800;
  private static final int VISUALIZER_HEIGHT = 525;

  private BorderPane myBorderPane;
  private UserInputPane myInputPane;
  private VisualizationPane myVisualizationPane;
  private CommandHistoryTab myCommandsTab;

  private LogicalController myLogicalController;

  public SlogoView (LogicalController control) {
    this.myLogicalController = control;
  }

  @Override
  public void start(Stage stage) throws IOException {
    myBorderPane = new BorderPane();
    myVisualizationPane = new VisualizationPane(VISUALIZER_WIDTH, VISUALIZER_HEIGHT);
    myInputPane = new UserInputPane(myLogicalController);
    Scene scene = new Scene(createGUIBorderPane(), WINDOW_WIDTH, WINDOW_HEIGHT);
    stage.setTitle("Parser Parser - Slogo Project - CS 308");
    scene.getStylesheets().add("stylesheets/defaultStyle.css");
    stage.setScene(scene);
    stage.show();
  }

  public BorderPane createGUIBorderPane() throws IOException {
    myBorderPane.setTop(getUpperPane());
    myBorderPane.setLeft(getLeftPane());
    Pane myCenterPane = getCenterPane();
    myBorderPane.setCenter(myCenterPane);
    myBorderPane.setRight(getRightPane());
    myBorderPane.setBottom(getBottomPane());

    myBorderPane.setMaxSize(WINDOW_WIDTH, WINDOW_HEIGHT);

    return myBorderPane;
  }

  private VBox getUpperPane() throws IOException {
    VBox vbox = new VBox();

    //MenuBar menu = new MenuPane().getNode();
    ToolBar tools = new ToolbarPane(this, myLogicalController).getNode();

    //vbox.getChildren().addAll(menu, tools);
    vbox.getChildren().addAll(tools);

    return vbox;
  }

  private TabPane getLeftPane() {
    TabPane tabPaneLeft = new TabPane();

    Tab definedFunctions = new DefinedFunctionsTab().getTab();
    Tab fileTree = new FileTreeTab().getTab();

    tabPaneLeft.getTabs().addAll(definedFunctions, fileTree);
    tabPaneLeft.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

    return tabPaneLeft;
  }

  private BorderPane getCenterPane() {
    BorderPane centerPane = new BorderPane();

    Group visualization = myVisualizationPane.getNode();
    HBox programInputArea = getProgramInputNode();

    centerPane.setCenter(visualization);
    centerPane.setBottom(programInputArea);

    return centerPane;
  }

  private HBox getProgramInputNode() {
    HBox programInputArea = new HBox();

    TextArea inputArea = myInputPane.getNode();
    inputArea.setPrefSize(WINDOW_WIDTH * 0.5, WINDOW_HEIGHT * 0.15);
    inputArea.setWrapText(true);

    VBox buttonArea = getButtonsVBox();

    programInputArea.getChildren().addAll(inputArea, buttonArea);
    programInputArea.setAlignment(Pos.CENTER);

    return programInputArea;
  }

  private VBox getButtonsVBox() {
    VBox buttonArea = new VBox();
    Button runButton = new Button("Run");
    runButton.setId("run-button");
    runButton.setMinSize(60, WINDOW_HEIGHT * 0.10);
    runButton.setPrefWidth(120);
    runButton.setOnAction(e -> {
      myInputPane.sendUserCommand();
    });

    Button clearButton = new Button("Clear");
    clearButton.setId("run-button");
    clearButton.setMinSize(60, WINDOW_HEIGHT * 0.05);
    clearButton.setPrefWidth(120);
    clearButton.setOnAction(e -> {
      myInputPane.clear();
    });

    buttonArea.getChildren().addAll(runButton, clearButton);
    return buttonArea;
  }

  private TabPane getRightPane() {
    TabPane tabPaneRight = new TabPane();

    Tab data = new DataViewerTab().getTab();
    myCommandsTab = new CommandHistoryTab(this);
    Tab commands = myCommandsTab.getTab();
    Tab errors = new ErrorHandlerTab().getTab();

    tabPaneRight.getTabs().addAll(commands, data, errors);
    tabPaneRight.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

    return tabPaneRight;
  }

  private static HBox getBottomPane() {
    return new CreditsPane().getNode();
  }

  public void announceError(){
    //TODO: Handle Announcing Errors
  }

  public void setUserInputAreaText(String fileContents) {
    myInputPane.setInputArea(fileContents);
  }

  public void updateVisualTurtles(ArrayList<VisualTurtle> visualTurtles) {
    for (VisualTurtle turtle : visualTurtles){
      myVisualizationPane.addVisualTurtle(turtle);
    }
    myBorderPane.setCenter(getCenterPane());
  }

  public void updateVisualLines(List<VisualLine> visualLines) {
    for (VisualLine line : visualLines){
      myVisualizationPane.addVisualLine(line);
    }
    myVisualizationPane.getNode();
    myBorderPane.setCenter(getCenterPane());
  }

  public void updateVisualCommands(List<VisualCommand> visualCommands) {

  }

  public void updateVisualErrors(List<VisualLine> visualLines) {

  }

  public void updateVisualData(List<VisualData> visualData) {

  }

  public void updateVisualUserFunctions(List<VisualUserFunction> visualFunctions) {

  }

//  public void doTestUpdate() {
//    Map<Integer, VisualTurtle> visualTurtles = new HashMap<>();
//
//    visualTurtles.add(new VisualTurtle());
//
//    VisualTurtle customTurtle = new VisualTurtle();
//    customTurtle.setChangeState(true);
//    customTurtle.setPreviousCenter(100, 100);
//    customTurtle.setCenter(200, 100);
//    customTurtle.setImage(TurtleImage.DOG);
//    customTurtle.setHeading(45);
//    customTurtle.setColor(Color.RED);
//    customTurtle.setSize(50);
//    visualTurtles.add(customTurtle);
//
//    updateVisualTurtles(visualTurtles);
//  }


  public void setBGColor(double red, double green, double blue) {
    myVisualizationPane.setBGColor(red, green, blue);
  }

  public void clearScreen() {
    myVisualizationPane.clearElements();
    myVisualizationPane.resetBGColor();
    myBorderPane.setCenter(getCenterPane());
  }

  public void setPenColor(double red, double green, double blue) {
    Color customColor = new Color(red,green,blue,1);
    myVisualizationPane.setPenColor(customColor);
  }
}