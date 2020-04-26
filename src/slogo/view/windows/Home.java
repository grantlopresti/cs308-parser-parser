package slogo.view.windows;

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
import slogo.Manager;

public class Home extends Application {

  private static final int WINDOW_WIDTH = 450;
  private static final int WINDOW_HEIGHT = 275;

  private String myDefaultLang = "ENGLISH";
  private String myColorScheme;

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
   * Entry point for application
   *
   * This creates the Splash screen using FXML, I am aware that this is not the ideal way of
   * doing it, but it was taken from my previous project and as I felt I demonstrated my ability
   * to properly use JavaFX for the main Slogo frame, I tried to save time here so other issues
   * could be addressed.
   *
   * @param primaryStage the main stage for the Home view
   * @throws IOException caused by FileInputStream for loading the FXML
   *
   * @author Grant LoPresti
   */
  @Override
  public void start(Stage primaryStage) throws IOException {
    FXMLLoader loader = new FXMLLoader();
    String fxmlPath = "src/slogo/view/windows/HomeView.fxml";
    System.out.println(System.getProperty("user.dir"));
    FileInputStream fis = new FileInputStream(fxmlPath);
    GridPane ap = loader.load(fis);
    ap.setVgap(5);
    ap.setPadding(new Insets(15, 15, 15, 15));
    Scene myHomeScene = new Scene(ap, WINDOW_WIDTH, WINDOW_HEIGHT);
    primaryStage.setScene(myHomeScene);
    primaryStage.show();
  }

  /**
   * Sets actions for buttons
   *  Both for launching the application and for changing settings
   *
   *  Methods were not able to be completed to implement the workspace settings buttons
   *  This could easily be done if we had designed/developed an API for doing so.
   *  As we were behind on much of the rest of the project, we did not reach the implementation
   *  of these more advanced features, but these buttons would have prompted pop-up windows that
   *  allowed the user to choose their settings and options for the workspace they were about to
   *  create
   *
   *  @author Grant LoPresti
   */
  public void initialize() {
    LaunchSlogo.setOnAction(e -> launchSim());
    //TODO: Add actual events to these button clicks
    ColorScheme.setOnAction(e -> System.out.println("ColorScheme"));
    Language.setOnAction(e -> System.out.println("Language Select"));
    Defaults.setOnAction(e -> System.out.println("Set Defaults"));
    Help.setOnAction(e -> System.out.println("Display Help Menu"));
  }

  /**
   * Lanuches the simulation by creating a new manager class with the default settings (these
   * setting currently include just the language, but would have been expanded to include the
   * remaining settings as well had there been more time/less other issues)
   *
   * @author Grant LoPresti
   */
  @FXML
  private void launchSim() {
    new Manager(myDefaultLang);
  }

  /**
   * Takes in a new language and sets this as the initial language for the workspace to have when
   * it is created. Later saved as a property of the workspace.
   * @param newLang is a string of the new language ex: "English"
   * @author Grant LoPresti
   */
  private void setDefaultLang(String newLang) {
    myDefaultLang = newLang;
  }

}
