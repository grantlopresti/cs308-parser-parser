package slogo.view.subsections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import slogo.view.SubTabFactory;
import slogo.view.TurtleImage;
import slogo.view.windows.SlogoView;
import slogo.visualcontroller.VisualController;

public class TurtleOptionsTab extends Tab {

  public static final String TAB_NAME = "Turtle Options";

  private static final String DEFAULT_TURTLE_IMAGE = "Turtle";

  private ComboBox<String> myTurtleChoices = new ComboBox<>();
  private ColorPicker myPenColorPicker = new ColorPicker();
  private GridPane myBonusCommandGrid = new GridPane();

  private VisualController myController;
  private SlogoView myViewer;

  private VBox myOrganizer;

  public TurtleOptionsTab(SlogoView viewer, VisualController controller) {
    super(TAB_NAME);
    myViewer = viewer;
    myController = controller;
    buildTab();
  }

  private void buildTab() {
    myOrganizer = new VBox();
    myOrganizer.getStyleClass().add("vBox");
    Label tabTitleLine = new Label("Turtle Options");
    Label instructions = new Label("Choose a Turtle Below to see its characteristics");
    for (TurtleImage value : TurtleImage.values()){
      myTurtleChoices.getItems().add(value.getName());
    }
    createBonusCommandGrid();
    initializeButtons();
    //turtleChoices.itemsProperty().bind(new SimpleObjectProperty<ObservableList<VisualTurtle>>
    // (myController.getTurtlesList()));
    myOrganizer.getChildren().addAll(
        tabTitleLine,
        instructions,
        new Separator(),
        new Text("Turtle Image:"),
        myTurtleChoices,
        new Text("Pen Color:"),
        myPenColorPicker,
        myBonusCommandGrid
    );
    setContent(myOrganizer);
  }

  private void createBonusCommandGrid() {

    List buttonNames = new ArrayList<Button>();

    Map bonusButtons = new HashMap<String, Button>();

    Button forwardButton = new Button("Forward");
    forwardButton.setId("bonus-button");
    Button backButton = new Button("Back");
    Button rightButton = new Button("Turn Right");
    Button leftButton = new Button("Turn Left");
    Button resetButton = new Button("Reset");

    GridPane.setHalignment(resetButton, HPos.CENTER);
    GridPane.setHalignment(backButton, HPos.CENTER);

    myBonusCommandGrid.setAlignment(Pos.CENTER);

    myBonusCommandGrid.add(forwardButton, 1, 0);
    myBonusCommandGrid.add(backButton, 1, 2);
    myBonusCommandGrid.add(rightButton, 2, 1);
    myBonusCommandGrid.add(leftButton, 0, 1);
    myBonusCommandGrid.add(resetButton, 1, 1);


  }

  private void initializeButtons() {
    setDefaultTurtleImage();
    myTurtleChoices.getSelectionModel().selectedItemProperty().addListener((options, oldValue,
        newValue) -> myViewer.changeTurtleImage(newValue));
    myPenColorPicker.setOnAction(t -> {
      Color c = myPenColorPicker.getValue();
      myViewer.setPenColor(c.getRed(), c.getGreen(), c.getBlue());
    });
  }

  private void setDefaultTurtleImage() {
    myTurtleChoices.setValue(DEFAULT_TURTLE_IMAGE);
    myViewer.changeTurtleImage(DEFAULT_TURTLE_IMAGE.toUpperCase());
  }
}
