package slogo.view.windows;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import slogo.view.TurtleImage;
import slogo.view.subsections.*;
import slogo.visualcontroller.VisualLine;
import slogo.visualcontroller.VisualTurtle;

public class SlogoView extends Application {

  private static final int WINDOW_WIDTH = 1355;
  private static final int WINDOW_HEIGHT = 700;

  private static final int VISUALIZER_WIDTH = 800;
  private static final int VISUALIZER_HEIGHT = 535;

  private BorderPane myBorderPane;
  private UserInputPane myInputPane;
  private VisualizationPane myVisualizationPane;

  @Override
  public void start(Stage stage) {
    myBorderPane = new BorderPane();
    myVisualizationPane = new VisualizationPane(VISUALIZER_WIDTH, VISUALIZER_HEIGHT);
    Scene scene = new Scene(createGUIBorderPane(), WINDOW_WIDTH, WINDOW_HEIGHT);
    stage.setTitle("Parser Parser - Slogo Project - CS 308");
    scene.getStylesheets().add("stylesheets/defaultStyle.css");
    stage.setScene(scene);
    stage.show();
  }

  public BorderPane createGUIBorderPane() {
    myBorderPane.setTop(getUpperPane());
    myBorderPane.setLeft(getLeftPane());
    Pane myCenterPane = getCenterPane();
    myBorderPane.setCenter(myCenterPane);
    myBorderPane.setRight(getRightPane());
    myBorderPane.setBottom(getBottomPane());

    myBorderPane.setMaxSize(WINDOW_WIDTH, WINDOW_HEIGHT);

    return myBorderPane;
  }

  private VBox getUpperPane() {
    VBox vbox = new VBox();

    //MenuBar menu = new MenuPane().getNode();
    ToolBar tools = new ToolbarPane(this).getNode();

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

    myInputPane = new UserInputPane();
    TextArea inputArea = myInputPane.getNode();
    inputArea.setPrefSize(WINDOW_WIDTH * 0.5, WINDOW_HEIGHT * 0.15);
    inputArea.setWrapText(true);

    Button runButton = new Button("Run");
    runButton.setId("run-button");
    runButton.setMinSize(60, WINDOW_HEIGHT * 0.15);
    runButton.setPrefWidth(120);
    runButton.setOnAction(e -> {
      myInputPane.sendUserCommand();
      //doTestUpdate();
    });

    programInputArea.getChildren().addAll(inputArea, runButton);
    programInputArea.setAlignment(Pos.CENTER);

    return programInputArea;
  }

  private TabPane getRightPane() {
    TabPane tabPaneRight = new TabPane();

    Tab data = new DataViewerTab().getTab();
    Tab commands = new CommandHistoryTab().getTab();
    Tab errors = new ErrorHandlerTab().getTab();

    tabPaneRight.getTabs().addAll(data, commands, errors);
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

  public void updateVisualTurtles(List<VisualTurtle> visualTurtles) {
    for (VisualTurtle turtle : visualTurtles){
      myVisualizationPane.addVisualTurtle(turtle);
    }
    myBorderPane.setCenter(getCenterPane());
  }

  public void updateVisualLines(List<VisualLine> visualLines) {
    System.out.println(myVisualizationPane);
    for (VisualLine line : visualLines){
      myVisualizationPane.addVisualLine(line);
    }
    myBorderPane.setCenter(getCenterPane());
  }

  public void doTestUpdate() {
    List<VisualTurtle> visualTurtles = new ArrayList<>();

    visualTurtles.add(new VisualTurtle());

    VisualTurtle customTurtle = new VisualTurtle();
    customTurtle.setChangeState(true);
    customTurtle.setCenter(100, 100);
    customTurtle.setImage(TurtleImage.DOG);
    customTurtle.setHeading(45);
    customTurtle.setColor(Color.RED);
    customTurtle.setSize(50);
    visualTurtles.add(customTurtle);

    updateVisualTurtles(visualTurtles);
  }


  public void setBGColor(double red, double green, double blue) {
    myVisualizationPane.setBGColor(red, green, blue);
    myBorderPane.setCenter(getCenterPane());
  }

  public void clearScreen() {
    myVisualizationPane.clearElements();
    myVisualizationPane.resetBGColor();
    myBorderPane.setCenter(getCenterPane());
  }
}