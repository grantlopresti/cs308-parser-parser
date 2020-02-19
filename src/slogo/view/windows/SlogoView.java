package slogo.view.windows;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import slogo.view.subpanes.MenuPane;
import slogo.view.subpanes.ToolbarPane;
import slogo.view.subpanes.UserInputPane;

public class SlogoView extends Application {

  private static final int WINDOW_WIDTH = 1440;
  private static final int WINDOW_HEIGHT = 720;

  public static void main(String[] args) {
    Application.launch(args);
  }

  @Override
  public void start(Stage stage) {
    Scene scene = new Scene(createBorderPane(), WINDOW_WIDTH, WINDOW_HEIGHT);
    stage.setTitle("Layout Demo");
    stage.setScene(scene);
    stage.show();
  }


  public BorderPane createBorderPane() {

    BorderPane borderPane = new BorderPane();

    borderPane.setTop(getUpperPane());
    borderPane.setLeft(getLeftPane());
    borderPane.setCenter(getCenterPane());
    borderPane.setRight(getRightPane());
    borderPane.setBottom(getBottomPane());

    return borderPane;
  }

  private HBox getBottomPane() {
    Label programCredits = new Label("Slogo - Parser Team 10");
    Label teamCredits = new Label("Created by: Alex Xu, Amjad Syedibrahim, Grant "
        + "LoPresti, and Max Smith");
    Label classCredits = new Label("CS 308 - Spring 2020 - Duvall");

    Region creditsSpacingLeft = new Region();
    HBox.setHgrow(creditsSpacingLeft, Priority.ALWAYS);

    Region creditsSpacingRight = new Region();
    HBox.setHgrow(creditsSpacingRight, Priority.ALWAYS);

    HBox creditsBar = new HBox();
    creditsBar.getChildren().addAll(programCredits, creditsSpacingLeft, teamCredits,
        creditsSpacingRight,
        classCredits);
    creditsBar.setAlignment(Pos.CENTER);
    creditsBar.setPadding(new Insets(3));

    return creditsBar;
  }

  private TabPane getRightPane() {
    TabPane tabPaneRight = new TabPane();
    tabPaneRight.getTabs().addAll(new Tab("Data/Variables"),
        new Tab("Command History"),
        new Tab("Error Handler"));
    return tabPaneRight;
  }

  private BorderPane getCenterPane() {
    TextArea inputPane = new UserInputPane().getNode();
    inputPane.setPrefSize(WINDOW_WIDTH*0.5,WINDOW_HEIGHT*0.15);
    inputPane.setWrapText(true);

    Button runButton = new Button("Run");
    runButton.setMinSize(40,WINDOW_HEIGHT*0.15);
    runButton.setPrefWidth(120);

    HBox programInputArea = new HBox();
    programInputArea.getChildren().addAll(inputPane, runButton);
    programInputArea.setAlignment(Pos.CENTER);

    BorderPane centerPane = new BorderPane();
    centerPane.setCenter(new TextArea());
    centerPane.setBottom(programInputArea);

    return centerPane;
  }

  private TabPane getLeftPane() {
    TreeView<String> projectsTree = createProjectsTree();

    TabPane tabPaneLeft = new TabPane();
    Tab projectsTab = new Tab("Project List");
    projectsTab.setContent(projectsTree);
    tabPaneLeft.getTabs().addAll(new Tab("Defined Functions"), projectsTab;
    return tabPaneLeft;
  }

  private VBox getUpperPane() {
    MenuPane menu = new MenuPane();
    ToolbarPane toolbar = new ToolbarPane();

    VBox vbox = new VBox();
    vbox.getChildren().addAll(menu.getNode(), toolbar.getNode());
    return vbox;
  }

  private TreeView<String> createProjectsTree() {
    TreeItem<String> projectsTree = new TreeItem<>("Projects");
    projectsTree.getChildren().addAll(
        new TreeItem<>("Project 1"),
        new TreeItem<>("Project 2"),
        new TreeItem<>("Project 3"),
        new TreeItem<>("Project 4"));
    return new TreeView<>(projectsTree);
  }

}