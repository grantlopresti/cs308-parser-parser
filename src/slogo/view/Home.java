package slogo.view;

import java.io.FileInputStream;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Home extends Application {

  private static final int WINDOW_WIDTH = 450;
  private static final int WINDOW_HEIGHT = 275;

  @FXML
  private Button LaunchSlogo;
  @FXML
  private Button ColorScheme;
  @FXML
  private Button Language;
  @FXML
  private Button Defaults;
  @FXML
  private Button Help;

  /**
   * Main method for the application - entry point
   */
  public static void main(String[] args) {
    Application.launch(args);
  }

  /**
   * Entry point for application
   *
   * @param primaryStage the main stage for the Home view
   * @throws IOException caused by FileInputStream for loading the FXML
   */
  @Override
  public void start(Stage primaryStage) throws IOException {
    FXMLLoader loader = new FXMLLoader();
    String fxmlPath = "src/slogo/view/HomeView.fxml";
    System.out.println(System.getProperty("user.dir"));
    FileInputStream fis = new FileInputStream(fxmlPath);
    GridPane ap = loader.load(fis);
    ap.setVgap(5);
    ap.setPadding(new Insets(15, 15, 15, 15));
    Scene myHomeScene = new Scene(ap, WINDOW_WIDTH, WINDOW_HEIGHT);
    primaryStage.setScene(myHomeScene);
    primaryStage.show();
  }

  @FXML
  private void launchSim() {
    SlogoView mainView = new SlogoView();
    try {
      mainView.start(new Stage());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

    /**
   * Sets actions for buttons for default simulations
   */
  public void initialize() {
    LaunchSlogo.setOnAction(e -> launchSim());
    ColorScheme.setOnAction(e -> launchSim("firespread.xml"));
    Language.setOnAction(e -> launchSim("gameoflifegun.xml"));
    Defaults.setOnAction(e -> launchSim("percolation.xml"));
    Help.setOnAction(e -> launchSim("predatorprey.xml"));
  }

  private void launchSim(String xmlFile) {
//    SimulationWindow simulationWindow = new SimulationWindow();
//    try {
//      simulationWindow.start(new Stage(), "data/" + xmlFile);
//    } catch (MalformedXMLException e) {
//      new Alert(AlertType.WARNING, "Malformed XML file - try another one", ButtonType.OK).show();
//    } catch (NumberFormatException e2) {
//      new Alert(AlertType.WARNING, "Error in XML file - try another one. "
//          + "Error message: " + e2.getMessage(), ButtonType.OK).show();
//    }
//  }
  }

}
