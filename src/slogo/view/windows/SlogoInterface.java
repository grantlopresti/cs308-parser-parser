package slogo.view.windows;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import slogo.logicalcontroller.LogicalController;
import slogo.view.subsections.*;
import slogo.visualcontroller.*;

public class SlogoView extends Application {

  private static final int WINDOW_WIDTH = 1380;
  private static final int WINDOW_HEIGHT = 700;

  private static final int VISUALIZER_WIDTH = 800;
  private static final int VISUALIZER_HEIGHT = 525;

  private BorderPane myMainPane;
  private UserInputPane myInputPane;
  private VisualizationPane myVisualizationPane;
  private CommandHistoryTab myCommandsTab;
  private DataViewerTab myDataTab;

  private LogicalController myLogicalController;
  private VisualController myVisualController;

  public SlogoView (LogicalController logicalController, VisualController visualController) {
    myLogicalController = logicalController;
    myVisualController = visualController;
  }

  @Override
  public void start(Stage stage) throws IOException {
    myMainPane = new BorderPane();
    myVisualizationPane = new VisualizationPane(VISUALIZER_WIDTH, VISUALIZER_HEIGHT);
    myInputPane = new UserInputPane(this, myLogicalController);
    Scene scene = new Scene(createGUIBorderPane(), WINDOW_WIDTH, WINDOW_HEIGHT);
    stage.setTitle("Parser Parser - Slogo Project - CS 308");
    scene.getStylesheets().add("stylesheets/defaultStyle.css");
    stage.setScene(scene);
    stage.show();
  }

  public BorderPane createGUIBorderPane() throws IOException {
    myMainPane.setTop(getUpperPane());
    myMainPane.setLeft(getLeftPane());
    Pane myCenterPane = getCenterPane();
    myMainPane.setCenter(myCenterPane);
    myMainPane.setRight(getRightPane());
    myMainPane.setBottom(getBottomPane());

    myMainPane.setMaxSize(WINDOW_WIDTH, WINDOW_HEIGHT);

    return myMainPane;
  }

  private VBox getUpperPane() throws IOException {
    VBox vbox = new VBox();

    ToolBar tools = new ToolbarPane(this, myLogicalController).getNode();

    vbox.getChildren().addAll(tools);

    return vbox;
  }

  private TabPane getLeftPane() {
    TabPane tabPaneLeft = new TabPane();

    Tab definedFunctions =
        new DefinedFunctionsTab(this).getTab(myVisualController.getProperty(VisualProperty.FUNCTION));
    //Tab fileTree = new FileTreeTab().getTab(myVisualController.getProperty(VisualProperty.FILE));

    tabPaneLeft.getTabs().addAll(definedFunctions); //, fileTree);
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

    ErrorHandlerTab errorTab = new ErrorHandlerTab(this);
    Tab errors = errorTab.getTab(myVisualController.getProperty(VisualProperty.ERROR));

    myCommandsTab = new CommandHistoryTab(this);
    myCommandsTab.setSlogoView(this);
    Tab commands = myCommandsTab.getTab(myVisualController.getProperty(VisualProperty.COMMAND));

    Tab data =
        new DataViewerTab(this).getTab(myVisualController.getProperty(VisualProperty.VARIABLE));

    tabPaneRight.getTabs().addAll(commands, data, errors);
    tabPaneRight.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

    return tabPaneRight;
  }

  private static HBox getBottomPane() {
    return new CreditsPane().getNode();
  }

  public void announceError(VisualError error){
    Alert alert;

    if (error.getSeverity() == ErrorSeverity.CRITICAL) {
      alert = new Alert(AlertType.ERROR);
    } else if (error.getSeverity() == ErrorSeverity.MEDIUM){
      alert = new Alert(AlertType.WARNING);
    } else {
      alert = new Alert(AlertType.INFORMATION);
    }

    alert.setTitle("Alert");
    alert.setHeaderText(null);
    alert.setContentText(error.toString());

    alert.showAndWait();
  }

  public void setUserInputAreaText(String fileContents) {
    myInputPane.setInputArea(fileContents);
  }

  public void updateVisualTurtles(ArrayList<VisualTurtle> visualTurtles) {
    for (VisualTurtle turtle : visualTurtles){
      myVisualizationPane.addVisualTurtle(turtle);
    }
    myMainPane.setCenter(getCenterPane());
  }

  public void updateVisualLines(List<VisualLine> visualLines) {
    for (VisualLine line : visualLines){
      myVisualizationPane.addVisualLine(line);
    }
    myVisualizationPane.getNode();
    myMainPane.setCenter(getCenterPane());
  }

  public void setBGColor(double red, double green, double blue) {
    myVisualizationPane.setBGColor(red, green, blue);
  }

  public void clearScreen() {
    myVisualizationPane.clearElements();
    myVisualizationPane.resetBGColor();
    myMainPane.setCenter(getCenterPane());
  }

  public void setPenColor(double red, double green, double blue) {
    Color customColor = new Color(red,green,blue,1);
    myVisualizationPane.setPenColor(customColor);
  }

  public void changeTurtleImage(String newValue) {
    myVisualController.changeTurtleImage(newValue.toUpperCase());
    //myVisualizationPane.
  }
}