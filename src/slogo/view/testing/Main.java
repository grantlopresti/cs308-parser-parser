package slogo.view.testing;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

  private static final int WINDOW_WIDTH = 1440;
  private static final int WINDOW_HEIGHT = 720;

  @Override
  public void start(Stage primaryStage) throws Exception {
    Pane root = new Pane();
    root.getChildren().add(constructWindow(0,0,1,12));
    root.getChildren().add(constructWindow(1,0,9,9));
    root.getChildren().add(constructWindow(1,9,5,3));
    root.getChildren().add(constructWindow(6,9,4,3));
    root.getChildren().add(constructWindow(10,0,3,9));
    root.getChildren().add(constructWindow(10,9,3,3));
    primaryStage.setScene(new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT));
    primaryStage.show();
  }


  public static void main(String[] args) {
    launch(args);
  }


  private InternalWindow constructWindow(int rowIndex, int colIndex, int rowSpan, int colSpan) {
    // content
    //ImageView imageView = new ImageView("https://upload.wikimedia" + ".org/wikipedia/commons/thumb/a/a9/Cheetah4.jpg/250px-Cheetah4.jpg");
    // title bar
    BorderPane titleBar = new BorderPane();
    titleBar.setStyle("-fx-background-color: green; -fx-padding: 3");
    Label label = new Label("header");
    titleBar.setLeft(label);
    Button closeButton = new Button("x");
    titleBar.setRight(closeButton);
    // title bat + content
    BorderPane windowPane = new BorderPane();
    windowPane.setStyle("-fx-border-width: 1; -fx-border-color: black");
    windowPane.setTop(titleBar);
    //windowPane.setCenter(imageView);

    //apply layout to InternalWindow
    InternalWindow interalWindow = new InternalWindow();
    interalWindow.setRoot(windowPane);
    //drag only by title
    //interalWindow.makeDragable(titleBar);
    //interalWindow.makeDragable(label);
    //interalWindow.makeResizable(20);
    interalWindow.setPosition(rowIndex, colIndex, rowSpan, colSpan);
    interalWindow.makeFocusable();
    interalWindow.setCloseButton(closeButton);
    return interalWindow;
  }
}