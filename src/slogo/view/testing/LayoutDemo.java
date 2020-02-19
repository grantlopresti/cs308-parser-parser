package slogo.view.testing;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class LayoutDemo extends Application {

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

    VBox vbox = createUpperVbox();

    TabPane tabPaneLeft = createTabPaneLeft();

    TabPane tabPaneRight = createTabPaneRight();

    borderPane.setTop(vbox);
    borderPane.setLeft(tabPaneLeft);
    borderPane.setCenter(new TextArea());
    borderPane.setRight(tabPaneRight);

    HBox programInputArea = new HBox();
    programInputArea.getChildren().addAll(new TextField(), new Button("Run"));

    VBox lowerPane = new VBox();
    lowerPane.getChildren().addAll(programInputArea, new Label("Status text: Borderpane demo"));

    borderPane.setBottom(lowerPane);

    return borderPane;
  }

  private VBox createUpperVbox() {
    MenuBar menuBar = createMenuBar();

    ToolBar toolbar = createToolBar();

    VBox vbox = new VBox();
    vbox.getChildren().addAll(menuBar, toolbar);
    return vbox;
  }

  private ToolBar createToolBar() {
    return new ToolBar(new Button("New"),
          new Button("Open"),
          new Separator(),
          new Button("Cut"),
          new Button("Copy"),
          new Button("Paste"));
  }

  private TabPane createTabPaneLeft() {

    TreeView<String> tv = createProjectsTree();

    TabPane tabPaneLeft = new TabPane();
    Tab tab1 = new Tab("Project List");
    tab1.setContent(tv);
    tabPaneLeft.getTabs().addAll(tab1, new Tab("Explorer"));
    return tabPaneLeft;
  }

  private TreeView<String> createProjectsTree() {
    TreeItem<String> ti = new TreeItem<>("Projects");
    ti.getChildren().addAll(
        new TreeItem<>("Project 1"),
        new TreeItem<>("Project 2"),
        new TreeItem<>("Project 3"),
        new TreeItem<>("Project 4"));
    return new TreeView<>(ti);
  }

  private TabPane createTabPaneRight() {
    TabPane tabPaneRight = new TabPane();
    tabPaneRight.getTabs().addAll(new Tab("Outline"),
        new Tab("Task List"));
    return tabPaneRight;
  }

  private MenuBar createMenuBar() {
    MenuBar menuBar = new MenuBar();
    Menu fileMenu = new Menu("File");
    fileMenu.getItems().addAll(new MenuItem("New"),
        new SeparatorMenuItem(),
        new MenuItem("Open"),
        new MenuItem("Save"),
        new MenuItem("Save As..."),
        new SeparatorMenuItem(),
        new MenuItem("Exit"));
    Menu editMenu = new Menu("Edit");
    editMenu.getItems().addAll(new MenuItem("Undo"),
        new MenuItem("Redo"),
        new MenuItem("Cut"),
        new MenuItem("Copy"),
        new MenuItem("Paste"),
        new SeparatorMenuItem(),
        new MenuItem("Search/Replace"));
    Menu helpMenu = new Menu("Help");
    helpMenu.getItems().addAll(new MenuItem("Help Contents"),
        new SeparatorMenuItem(),
        new MenuItem("About..."));
    menuBar.getMenus().addAll(fileMenu, editMenu, helpMenu);
    return menuBar;
  }
}