package slogo.view.windows;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import slogo.Manager;
import slogo.exceptions.ResourceBundleException;
import slogo.logicalcontroller.BundleInterface;

public class Home2 extends Application {

  private static final int WINDOW_WIDTH = 450;
  private static final int WINDOW_HEIGHT = 275;

  public static final String BUTTONS_RESOURCE = "src/slogo/view/resources/homeButtons.properties";
  private ResourceBundle myButtonsResource;

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
  public void start(Stage primaryStage)
      throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
    makeMainGrid();
    Scene myHomeScene = new Scene(myMainGrid, WINDOW_WIDTH, WINDOW_HEIGHT);
    primaryStage.setScene(myHomeScene);
    primaryStage.show();
  }

  private void makeMainGrid()
      throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
    myMainGrid = new GridPane();
    myMainGrid.setVgap(5);
    myMainGrid.setPadding(new Insets(15, 15, 15, 15));

    try {
      myButtonsResource = BundleInterface.createResourceBundle(BUTTONS_RESOURCE);
    } catch (IOException e) {
      throw new ResourceBundleException();
    }

    for (String key : Collections.list(myButtonsResource.getKeys())) {
      String itemClass = myButtonsResource.getString(key).split(",")[0];
      String itemText = myButtonsResource.getString(key).split(",")[1];
      Class clazz = Class.forName("javafx.scene.control." + itemClass);
      Node toAdd = (Node) clazz.getConstructor().newInstance();
      int itemCol = Integer.parseInt(myButtonsResource.getString(key).split(",")[2]);
      int itemRow = Integer.parseInt(myButtonsResource.getString(key).split(",")[3]);
      int itemSpan = Integer.parseInt(myButtonsResource.getString(key).split(",")[4]);
      String method = myButtonsResource.getString(key).split(",")[5];
      myMainGrid.add(toAdd, itemCol, itemRow, itemSpan, 1);
    }
  }

  private void launchSim() throws IOException {
    new Manager(myDefaultLang);
  }

  private void setDefaultLang(String newLang) {
    myDefaultLang = newLang;
  }

}
