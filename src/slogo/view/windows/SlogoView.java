package slogo.view.windows;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import slogo.logicalcontroller.LogicalController;
import slogo.view.SubTabFactory;
import slogo.view.subsections.ListTab;
import slogo.view.subsections.ToolbarPane;
import slogo.view.subsections.TurtleOptionsTab;
import slogo.view.subsections.UserInputPane;
import slogo.view.subsections.VisualizationPane;
import slogo.visualcontroller.ErrorSeverity;
import slogo.visualcontroller.VisualController;
import slogo.visualcontroller.VisualError;
import slogo.visualcontroller.VisualLine;
import slogo.visualcontroller.VisualProperty;
import slogo.visualcontroller.VisualTurtle;

public class SlogoView extends Application {

  private static final int VISUALIZER_WIDTH = 800;
  private static final int VISUALIZER_HEIGHT = 525;
  public static final int RIGHT_PANE_WIDTH = 350;
  public static final int LEFT_PANE_WIDTH = 350;

  private static final int WINDOW_WIDTH = LEFT_PANE_WIDTH + VISUALIZER_WIDTH + RIGHT_PANE_WIDTH;
  private static final int WINDOW_HEIGHT = 700;

  public static final String PROJECT_TITLE = "Parser Parser - Slogo Project - CS 308";
  public static final String DEFAULT_STYLESHEET = "stylesheets/defaultStyle.css";
  public static final String CREATORS_CREDIT = "Created by: Alex Xu, Amjad Syedibrahim, Grant LoPresti, and Max Smith";
  public static final String CLASS_CREDIT = "CS 308 - Spring 2020 - Duvall";
  public static final String PROJECT_CREDIT = "Slogo - Parser Team 10";

  //FIXME: Delete these and replace with XML reading
  public static final String[] INITIAL_LEFT_TAB_NAMES = new String[]{
      "UserDefinedFunctionsTab",
      "ErrorHandlerTab",
      "FileViewerTab"
  };
  public static final String[] INITIAL_RIGHT_TAB_NAMES = new String[]{
      "CommandHistoryTab",
      "VariableViewerTab",
      "DataViewerTab",
  };
  public static final String ALERT_TITLE = "Alert";

  //Main Sections
  private BorderPane myMainPane;
  private ToolBar myToolbarPane;
  private TabPane myLeftPane;
  private BorderPane myCenterPane;
  private TabPane myRightPane;
  private Pane myCreditsPane;

  //SubPanes
  private VisualizationPane myVisualizationPane;
  private UserInputPane myInputPane;
  private TurtleOptionsTab myTurtleOptionsTab;

  //Controllers
  private LogicalController myLogicalController;
  private VisualController myVisualController;

  //Factories
  private SubTabFactory mySubTabFactory;

  private ResourceBundle myTabTypeResources;
  private ResourceBundle myInitialLeftTabResources;
  private ResourceBundle myInitialRightTabResources;

  private static final String TabResourcePath = "slogo.view.resources.possibleTabs";
  private static final String InitialLeftTabResourcePath = "slogo.view.resources.InitialLeftTabs";
  private static final String InitialRightTabResourcePath = "slogo.view.resources.InitialRightTabs";


  public SlogoView(LogicalController logicalController, VisualController visualController) {
    myLogicalController = logicalController;
    myVisualController = visualController;

    myTabTypeResources = ResourceBundle.getBundle(TabResourcePath);
    myInitialLeftTabResources = ResourceBundle.getBundle(InitialLeftTabResourcePath);
    myInitialRightTabResources = ResourceBundle.getBundle(InitialRightTabResourcePath);
  }

  @Override
  public void start(Stage stage) throws IOException {
    Scene scene = new Scene(createMainPane(), WINDOW_WIDTH, WINDOW_HEIGHT);
    stage.setTitle(PROJECT_TITLE);
    scene.getStylesheets().add(DEFAULT_STYLESHEET);
    stage.setScene(scene);
    stage.show();
  }

  public BorderPane createMainPane() throws IOException {
    myMainPane = new BorderPane();

    createSubPanes();
    myMainPane.setTop(myToolbarPane);
    myMainPane.setLeft(myLeftPane);
    myMainPane.setCenter(myCenterPane);
    myMainPane.setRight(myRightPane);
    myMainPane.setBottom(myCreditsPane);

    myMainPane.setMaxSize(WINDOW_WIDTH, WINDOW_HEIGHT);

    return myMainPane;
  }

  private void createSubPanes() throws IOException {
    mySubTabFactory = new SubTabFactory();

    createToolbarPane();
    createLeftPane();
    createCenterPane();
    createRightPane();
    createCreditsPane();
  }

  private void createToolbarPane() throws IOException {
    myToolbarPane = new ToolbarPane(this, myLogicalController).getNode();
  }

  private void createCenterPane() {
    myCenterPane = new BorderPane();

    myVisualizationPane = new VisualizationPane(VISUALIZER_WIDTH, VISUALIZER_HEIGHT);
    myVisualizationPane.update();
    HBox programInputArea = getProgramInputNode();

    myCenterPane.setCenter(myVisualizationPane);
    myCenterPane.setBottom(programInputArea);
  }

  private HBox getProgramInputNode() {
    HBox programInputArea = new HBox();

    myInputPane = new UserInputPane(this, myLogicalController);
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

  private void createLeftPane() {
    myLeftPane = new TabPane();

    //FIXME: Initial settings should be obtained from XML File
    for (String initialTabName : INITIAL_LEFT_TAB_NAMES){
      myLeftPane.getTabs().add(mySubTabFactory.makeTab(this, myVisualController, initialTabName));
    }

    myTurtleOptionsTab = new TurtleOptionsTab(this, myVisualController);
    myLeftPane.getTabs().add(myTurtleOptionsTab);

    myLeftPane.setPrefWidth(LEFT_PANE_WIDTH);
  }

  private void createRightPane() {
    myRightPane = new TabPane();

    for (String initialTabName : INITIAL_RIGHT_TAB_NAMES){
      myRightPane.getTabs().add(mySubTabFactory.makeTab(this, myVisualController, initialTabName));
    }

    myRightPane.setPrefWidth(RIGHT_PANE_WIDTH);

  }

  private void createCreditsPane() {
    myCreditsPane = new HBox();

    Region creditsSpacingLeft = new Region();
    HBox.setHgrow(creditsSpacingLeft, Priority.ALWAYS);

    Region creditsSpacingRight = new Region();
    HBox.setHgrow(creditsSpacingRight, Priority.ALWAYS);

    myCreditsPane.getChildren().addAll(
        new Label(PROJECT_CREDIT),
        creditsSpacingLeft,
        new Label(CREATORS_CREDIT),
        creditsSpacingRight,
        new Label(CLASS_CREDIT)
    );

    ((HBox)myCreditsPane).setAlignment(Pos.CENTER);
    myCreditsPane.setPadding(new Insets(3));
  }


  public void announceError(VisualError error) {
    Alert alert;

    if (error.getSeverity() == ErrorSeverity.CRITICAL) {
      alert = new Alert(AlertType.ERROR);
    } else if (error.getSeverity() == ErrorSeverity.MEDIUM){
      alert = new Alert(AlertType.WARNING);
    } else {
      alert = new Alert(AlertType.INFORMATION);
    }

    alert.setTitle(ALERT_TITLE);
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
    myVisualizationPane.update();
    myMainPane.setCenter(myCenterPane);
  }

  public void updateVisualLines(List<VisualLine> visualLines) {
    for (VisualLine line : visualLines){
      myVisualizationPane.addVisualLine(line);
    }
    myVisualizationPane.update();
    myMainPane.setCenter(myCenterPane);
  }

  public void setBGColor(double red, double green, double blue) {
    myVisualizationPane.setBGColor(red, green, blue);
  }

  public void clearScreen() {
    myVisualizationPane.clearElements();
    myVisualizationPane.resetBGColor();
    myMainPane.setCenter(myCenterPane);
  }

  public void setPenColor(double red, double green, double blue) {
    Color customColor = new Color(red,green,blue,1);
    myVisualizationPane.setPenColor(customColor);
  }

  public void changeTurtleImage(String newValue) {
    myVisualController.changeTurtleImage(newValue.toUpperCase());
    //TODO: UPDATE CENTER
    //myVisualizationPane.
  }
}