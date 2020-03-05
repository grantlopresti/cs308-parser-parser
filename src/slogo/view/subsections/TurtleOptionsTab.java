package slogo.view.subsections;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;
import slogo.visualcontroller.VisualController;

public class TurtleOptionsTab extends Tab {

  public static final String TAB_NAME = "Turtle Options";

  private VisualController myController;

  private VBox myOrganizer;

  public TurtleOptionsTab(VisualController controller) {
    super(TAB_NAME);
    myController = controller;
    buildTab();
  }

  private void buildTab() {
    myOrganizer = new VBox();
    myOrganizer.getStyleClass().add("vBox");
    Label tabTitleLine = new Label("Turtle Options");
    Label instructions = new Label("Choose a Turtle Below to see its characteristics");
    ComboBox<String> turtleChoices = new ComboBox<>();
    turtleChoices.itemsProperty().bind(new SimpleObjectProperty<>(myController.));
    myOrganizer.getChildren().addAll(
        tabTitleLine,
        instructions,
        turtleChoices
    );
    setContent(myOrganizer);
  }
}
