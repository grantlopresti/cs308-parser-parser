package slogo.view.windows;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import slogo.view.subpanes.MenuPane;
import slogo.view.subpanes.ToolbarPane;

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

  private VBox getBottomPane() {
    HBox programInputArea = new HBox();
    programInputArea.getChildren().addAll(new TextField(), new Button("Run"));
    programInputArea.setAlignment(Pos.CENTER);

    Label programCredits = new Label("Slogo | Created by: Alex Xu, Amjad Syedibrahim, Grant "
          + "LoPresti, and Max Smith | CS 308 - Spring 2020");

    VBox lowerPane = new VBox();
    lowerPane.getChildren().addAll(programInputArea, programCredits);
    lowerPane.setAlignment(Pos.CENTER);

    return lowerPane;
  }

  private TabPane getRightPane() {
    TabPane tabPaneRight = new TabPane();
    tabPaneRight.getTabs().addAll(new Tab("Outline"),
        new Tab("Task List"));
    return tabPaneRight;
  }

  private TextArea getCenterPane() {
    return new TextArea();
  }

  private TabPane getLeftPane() {
    TreeView<String> tv = createProjectsTree();

    TabPane tabPaneLeft = new TabPane();
    Tab tab1 = new Tab("Project List");
    tab1.setContent(tv);
    tabPaneLeft.getTabs().addAll(tab1, new Tab("Explorer"));
    return tabPaneLeft;
  }

  private VBox getUpperPane() {
    MenuPane menu = new MenuPane();
    ToolbarPane toolbar = new ToolbarPane();

    VBox vbox = new VBox();
    vbox.getChildren().addAll(menu.getPane(), toolbar.getPane());
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