package slogo.view.windows;

import java.io.FileInputStream;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import slogo.Manager;

public class Home2 extends Application {

  private static final int WINDOW_WIDTH = 450;
  private static final int WINDOW_HEIGHT = 275;

  private GridPane myMainGrid;

  private String myDefaultLang = "ENGLISH";
  private String myColorScheme = "LIGHT";

  private Button LaunchSlogo;
  private Button ColorScheme;
  private Button Language;
  private Button Help;

  /**
   * Entry point for application
   * @param primaryStage the main stage for the Home view
   * @throws IOException caused by FileInputStream for loading the FXML
   */
  @Override
  public void start(Stage primaryStage) {
    makeMainGrid();
    Scene myHomeScene = new Scene(myMainGrid, WINDOW_WIDTH, WINDOW_HEIGHT);
    primaryStage.setScene(myHomeScene);
    primaryStage.show();
  }

  private void makeMainGrid() {
    myMainGrid = new GridPane();
    myMainGrid.setVgap(5);
    myMainGrid.setPadding(new Insets(15, 15, 15, 15));

    LaunchSlogo = new Button();
    ColorScheme = new Button();
    Language = new Button();
    Help = new Button();

    //myMainGrid.add();

  }

  /**
   * Sets actions for buttons
   *  Both for launching the application
   *  and for changing settings
   */
  public void initialize() {
    LaunchSlogo.setOnAction(e -> {
      try {
        launchSim();
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    });
    //TODO: Add actual events to these button clicks
    ColorScheme.setOnAction(e -> System.out.println("ColorScheme"));
    Language.setOnAction(e -> System.out.println("Language Select"));
    Help.setOnAction(e -> System.out.println("Display Help Menu"));
  }

  private void launchSim() throws IOException {
    new Manager(myDefaultLang);
  }

  private void setDefaultLang(String newLang) {
    myDefaultLang = newLang;
  }

}
