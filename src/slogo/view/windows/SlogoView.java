package slogo.view.windows;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import slogo.view.subsections.*;

public class SlogoView extends Application {

  private static final int WINDOW_WIDTH = 1440;
  private static final int WINDOW_HEIGHT = 720;

  private UserInputPane myInputPane;

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

    borderPane.setMaxSize(WINDOW_WIDTH, WINDOW_HEIGHT);

    return borderPane;
  }

  private VBox getUpperPane() {
    VBox vbox = new VBox();

    MenuBar menu = new MenuPane().getNode();
    ToolBar tools = new ToolbarPane(this).getNode();

    vbox.getChildren().addAll(menu, tools);

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

    GridPane visualization = new VisualizationPane().getNode();
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
    runButton.setMinSize(60, WINDOW_HEIGHT * 0.15);
    runButton.setPrefWidth(120);
    runButton.setOnAction(e -> myInputPane.sendUserCommand());

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

}