package slogo.view.windows;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import slogo.logicalcontroller.LogicalController;
import slogo.view.SubTabFactory;
import slogo.view.subsections.SubTab;
import slogo.visualcontroller.VisualController;

public class SlogoInterface extends Application {

  private static final int WINDOW_WIDTH = 1380;
  private static final int WINDOW_HEIGHT = 700;

  private static final int VISUALIZER_WIDTH = 800;
  private static final int VISUALIZER_HEIGHT = 525;

  //Main Sections
  private BorderPane myMainPane;
  private Pane myToolbarPane;
  private TabPane myLeftPane;
  private Pane myCenterPane;
  private TabPane myRightPane;
  private Pane myCreditsPane;

  //SubPanes
  private Pane myVisualizationPane;
  private Pane myInputPane;

  //SubTabs
  private Tab myCommandsTab;
  private Tab myDataTab;
  private Tab myFunctionsTab;
  private Tab myErrorsTab;
  private Tab myTurtlesTab;

  //Controllers
  private LogicalController myLogicalController;
  private VisualController myVisualController;

  //Factories
  //private SubTabFactory mySubTabFactory;

  public SlogoInterface(LogicalController logicalController, VisualController visualController) {
    myLogicalController = logicalController;
    myVisualController = visualController;
  }

  @Override
  public void start(Stage stage) {
    Scene scene = new Scene(createMainPane(), WINDOW_WIDTH, WINDOW_HEIGHT);
    stage.setTitle("Parser Parser - Slogo Project - CS 308");
    scene.getStylesheets().add("stylesheets/defaultStyle.css");
    stage.setScene(scene);
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
    //mySubTabFactory = new SubTabFactory();

    myToolbarPane = new Pane();
    myLeftPane = new TabPane();
    myCenterPane = new Pane();
    createRightPane();
    createCreditsPane();
  }

  private void createRightPane() {
    //FIXME: Initial settings should be obtained from XML File
    String[] initialTabNames = new String[]{"CommandHistoryTab", "DataViewerTab", "ErrorHandlerTab"};
    SubTab[] initialTabs = new SubTab[initialTabNames.length];
    for (int i = 0; i < initialTabNames.length; i++){
      //initialTabs[i] = mySubTabFactory.promptForTab(initialTabNames[i]);
    }

  }

  private void createCreditsPane() {
    myCreditsPane = new HBox();

    Region creditsSpacingLeft = new Region();
    HBox.setHgrow(creditsSpacingLeft, Priority.ALWAYS);

    Region creditsSpacingRight = new Region();
    HBox.setHgrow(creditsSpacingRight, Priority.ALWAYS);

    myCreditsPane.getChildren().addAll(
        new Label("Slogo - Parser Team 10"),
        creditsSpacingLeft,
        new Label("Created by: Alex Xu, Amjad Syedibrahim, Grant LoPresti, and Max Smith"),
        creditsSpacingRight,
        new Label("CS 308 - Spring 2020 - Duvall")
    );

    ((HBox)myCreditsPane).setAlignment(Pos.CENTER);
    myCreditsPane.setPadding(new Insets(3));
  }


}