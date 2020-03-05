package slogo.view.testing;

import java.awt.Dimension;
import java.util.List;
import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * An example class to show how to bind data to UI directly.
 *
 * Disclaimer: The code here for the GUI is poorly written (everything is done in this class and
 * hardcoded), and is merely to demonstrate how to set up a basic binding between two objects.
 *
 * @author Jonathan Tseng
 * @author Robert C. Duvall
 */
public class testBinding extends Application {
  public static final String TITLE = "Object Binding Example";
  public static final Dimension DEFAULT_SIZE = new Dimension(600, 200);

  // the Model: create collection that can be linked to by View components
  private ObservableList<String> myWords = FXCollections.observableArrayList(List.of("a", "b", "c"));


  /**
   * @see testBinding#start(Stage)
   */
  @Override
  public void start (Stage primaryStage) {
    primaryStage.setTitle(TITLE);
    primaryStage.setScene(makeScene(DEFAULT_SIZE.width, DEFAULT_SIZE.height));
    primaryStage.show();
  }

  // organize areas within a scene
  private Scene makeScene (int width, int height) {
    BorderPane root = new BorderPane();
    root.setTop(makeInput());
    root.setCenter(makeBindings());
    return new Scene(root, width,  height);
  }

  // organize user input area
  private Node makeInput () {
    Label prompt = new Label("How was your day? ");
    // input new values into observable collection
    TextField input = new TextField();
    input.setOnAction(e -> {
      // NOTE, no code here to update any View components!
      myWords.add(input.getText());
      input.setText("");
    });

    HBox result = new HBox();
    result.getChildren().addAll(prompt, input);
    return result;
  }

  // organize example components to show off binding examples
  private Node makeBindings () {
    // bind available choices to observable collection
    ComboBox<String> choices = new ComboBox<>();
    choices.itemsProperty().bind(new SimpleObjectProperty<>(myWords));
    // update manually: set label text to newly added value in collection
    Label mirror = new Label();
    myWords.addListener((ListChangeListener<String>)c -> mirror.setText(myWords.get(myWords.size() - 1)));
    // update automatically: bind label text property to current value property within combo box
    Label output = new Label();
    output.textProperty().bind(choices.valueProperty());

    VBox result = new VBox();
    result.getChildren().addAll(mirror, output, choices);
    return result;
  }

  /**
   * Start of the program --- hand control off to JavaFX.
   */
  public static void main (String[] args) {
    launch(args);
  }
}
