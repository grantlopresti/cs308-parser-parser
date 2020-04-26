package slogo.view.windows;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
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
import slogo.exceptions.InvalidCommandException;
import slogo.exceptions.ResourceBundleException;
import slogo.logicalcontroller.BundleInterface;
import slogo.logicalcontroller.LogicalController;
import slogo.view.SubTabFactory;
import slogo.view.subsections.ToolbarPane;
import slogo.view.subsections.TurtleOptionsTab;
import slogo.view.subsections.UserInputPane;
import slogo.view.subsections.VisualizationPane;
import slogo.visualcontroller.ErrorSeverity;
import slogo.visualcontroller.VisualController;
import slogo.visualcontroller.VisualError;
import slogo.visualcontroller.VisualLine;
import slogo.visualcontroller.VisualTurtle;

/**
 * Main View Class
 *
 * @author Grant LoPresti
 */
public class SlogoView extends Application {

  private static final int VISUALIZER_WIDTH = 800;
  private static final int VISUALIZER_HEIGHT = 525;
  public static final int RIGHT_PANE_WIDTH = 375;
  public static final int LEFT_PANE_WIDTH = 325;
  private static final int ANIMATION_RATE = 3;

  private static final int WINDOW_WIDTH = LEFT_PANE_WIDTH + VISUALIZER_WIDTH + RIGHT_PANE_WIDTH;
  private static final int WINDOW_HEIGHT = 700;

  public static final String DEFAULT_STYLESHEET = "stylesheets/defaultStyle.css";
  public Boolean isDarkMode = false;

  public static final String PROJECT_TITLE = "Parser Parser - Slogo Project - CS 308";
  public static final String CREATORS_CREDIT = "Created by: Alex Xu, Amjad Syedibrahim, Grant LoPresti, and Max Smith";
  public static final String CLASS_CREDIT = "CS 308 - Spring 2020 - Duvall";
  public static final String PROJECT_CREDIT = "Slogo - Parser Team 10";

  public static final String POSSIBLE_TABS_RESOURCE = "src/slogo/view/resources/possibleTabs.properties";
  private ResourceBundle myPossibleTabsResource;


  //FIXME: Delete these and replace with XML reading
  public static final String[] INITIAL_RIGHT_TAB_NAMES = new String[]{
      "CommandHistoryTab",
      "VariableViewerTab",
      "UserDefinedFunctionsTab",
      "ErrorHandlerTab"
  };
  public static final String ALERT_TITLE = "Alert";

  //Main Sections
  private BorderPane myMainPane;
  private ToolBar myToolbarPane;
  private VBox myLeftPane;
  private BorderPane myCenterPane;
  private VBox myRightPane;
  private Pane myCreditsPane;

  //SubPanes
  private TabPane myLeftTabPane;
  private TabPane myRightTabPane;
  private VisualizationPane myVisualizationPane;
  private UserInputPane myInputPane;
  private TurtleOptionsTab myTurtleOptionsTab;

  //Controllers
  private LogicalController myLogicalController;
  private VisualController myVisualController;

  //Factories
  private SubTabFactory mySubTabFactory;

  public SlogoView(LogicalController logicalController, VisualController visualController) {
    myLogicalController = logicalController;
    myVisualController = visualController;
    myVisualController.setAnimationRate(ANIMATION_RATE);
  }

  private Scene myScene;

  @Override
  public void start(Stage stage) {
    myScene = new Scene(createMainPane(), WINDOW_WIDTH, WINDOW_HEIGHT);
    stage.setTitle(PROJECT_TITLE);
    myScene.getStylesheets().add(DEFAULT_STYLESHEET);
    myScene.setFill(Color.DARKGRAY);
    stage.setScene(myScene);
    stage.show();
  }

  public BorderPane createMainPane() {
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

  private void createSubPanes() {
    mySubTabFactory = new SubTabFactory();

    createToolbarPane();
    createLeftPane();
    createCenterPane();
    createRightPane();
    createCreditsPane();
  }

  private void createToolbarPane() {
    myToolbarPane = new ToolbarPane(this).getNode();
  }

  private void createCenterPane() {
    myCenterPane = new BorderPane();

    myVisualizationPane = new VisualizationPane(VISUALIZER_WIDTH, VISUALIZER_HEIGHT, ANIMATION_RATE);
    addInitialTurtle();
    myVisualizationPane.update();
    HBox programInputArea = getProgramInputNode();

    myCenterPane.setCenter(myVisualizationPane);
    myCenterPane.setBottom(programInputArea);
  }

  private void addInitialTurtle() {
    VisualTurtle initialTurtle = new VisualTurtle();
    myVisualizationPane.addVisualTurtle(initialTurtle);
    myTurtleOptionsTab.addTurtle(initialTurtle);
  }

  private HBox getProgramInputNode() {
    HBox programInputArea = new HBox();

    myInputPane = new UserInputPane();
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
    runButton.setOnAction(e -> sendUserCommand(myInputPane.getCommand()));

    Button clearButton = new Button("Clear");
    clearButton.setId("run-button");
    clearButton.setMinSize(60, WINDOW_HEIGHT * 0.05);
    clearButton.setPrefWidth(120);
    clearButton.setOnAction(e -> myInputPane.clear());

    buttonArea.getChildren().addAll(runButton, clearButton);
    return buttonArea;
  }

  public void sendUserCommand(String userCommand) {
    try {
      if (!userCommand.equals("")) {
        myLogicalController.handleNewCommand(userCommand);
      } else {
        announceError(new VisualError(new InvalidCommandException("This command has no "
            + "body")));
      }
    }
    catch (Exception e){
      announceError(new VisualError(new InvalidCommandException("The following command "
          + "is invalid, please try another!\n" + userCommand)));
    }
  }

  private void createLeftPane() {
    myLeftPane = new VBox();

    myLeftTabPane = new TabPane();
    myTurtleOptionsTab = new TurtleOptionsTab(this);
    Tab colorPaletteTab = new Tab("Colors");
    Tab imagePaletteTab = new Tab("Images");
    myLeftTabPane.getTabs().addAll(myTurtleOptionsTab, imagePaletteTab, colorPaletteTab);
    myLeftTabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
    myLeftTabPane.setPrefWidth(LEFT_PANE_WIDTH);

    myLeftPane.getChildren().addAll(myLeftTabPane);
  }

  private void createRightPane() {
    myRightPane = new VBox();

    MenuBar rightNewTab = new MenuBar();
    Menu menu = new Menu("Choose Tab to Add to Right Pane");
    rightNewTab.getMenus().add(menu);

    setPossibleTabsList();
    setTabMenuItems(menu);

    myRightTabPane = new TabPane();
    addInitialTabs();
    myRightTabPane.setPrefWidth(RIGHT_PANE_WIDTH);
    myRightPane.getChildren().addAll(rightNewTab, myRightTabPane);
  }

  private void setPossibleTabsList() {
    try {
      myPossibleTabsResource = BundleInterface.createResourceBundle(POSSIBLE_TABS_RESOURCE);
    } catch (IOException e) {
      throw new ResourceBundleException();
    }
  }

  private void addInitialTabs() {
    for (String initialTabName : INITIAL_RIGHT_TAB_NAMES){
      myRightTabPane.getTabs().add(mySubTabFactory.makeTab(this, myVisualController, initialTabName));
    }
  }

  private void setTabMenuItems(Menu menu) {
    for (String key : Collections.list(myPossibleTabsResource.getKeys())) {
      MenuItem menuItem = new MenuItem(myPossibleTabsResource.getString(key).split(",")[0]);
      menuItem.setOnAction(e -> {
        Tab newTab = mySubTabFactory.makeTab(this, myVisualController, key);
        myRightTabPane.getTabs().add(newTab);
      });
      menu.getItems().add(menuItem);
    }
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

    alert = getAlert(error);

    alert.setTitle(ALERT_TITLE);
    alert.setHeaderText(null);
    alert.setContentText(error.toString());

    alert.showAndWait();
  }

  private Alert getAlert(VisualError error) {
    Alert alert;
    if (error.getSeverity() == ErrorSeverity.CRITICAL) {
      alert = new Alert(AlertType.ERROR);
    } else if (error.getSeverity() == ErrorSeverity.MEDIUM){
      alert = new Alert(AlertType.WARNING);
    } else {
      alert = new Alert(AlertType.INFORMATION);
    }
    return alert;
  }

  public void setUserInputAreaText(String fileContents) {
    myInputPane.setInputArea(fileContents);
  }

  public void updateVisualTurtles(List<VisualTurtle> visualTurtles) {
    for (VisualTurtle turtle : visualTurtles){
      myVisualizationPane.addVisualTurtle(turtle);
      myTurtleOptionsTab.addTurtle(turtle);
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
  }

  public void setPenColor(double red, double green, double blue) {
    //Color customColor = new Color(red,green,blue,1);
    //TODO: SEND COLOR CHANGE COMMAND TO LOGICAL CONTROLLER
  }

  public void changeTurtleImage(int ID, String newValue) {
    myVisualController.changeTurtleImage(newValue.toUpperCase());
    myVisualizationPane.changeTurtleImage(ID, newValue.toUpperCase());
  }

  public void toggleDarkMode() {
    System.out.println(isDarkMode);
    if (isDarkMode) {
      switchStyleMode("stylesheets/darkMode.css", "stylesheets/defaultStyle.css", false);
    } else {
      switchStyleMode("stylesheets/defaultStyle.css", "stylesheets/darkMode.css", true);
    }
  }

  private void switchStyleMode(String oldSheet, String newSheet, boolean isDark) {
    myScene.getStylesheets().remove(oldSheet);
    myScene.getStylesheets().add(newSheet);
    isDarkMode = isDark;
  }

  public void setLanguage(String language) {
    myLogicalController.setLanguage(language);
  }

  public void handleNewCommand(String fileContents) {
    myLogicalController.handleNewCommand(fileContents);
  }
}