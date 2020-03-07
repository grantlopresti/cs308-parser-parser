package slogo.view.subsections;

import java.util.HashMap;
import java.util.Map;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import slogo.view.TurtleImage;
import slogo.view.windows.SlogoView;
import slogo.visualcontroller.VisualTurtle;

public class TurtleOptionsTab extends Tab {

  public static final String TAB_NAME = "Turtle Options";

  private static final String DEFAULT_TURTLE_IMAGE = "Turtle";
  public static final String TURTLE_STATS = "Turtle Stats:";
  public static final String TURTLE_ID = "\tTurtle ID:\t\t";
  public static final String TURTLE_X_POS = "\tTurtle X-Pos:\t";
  public static final String TURTLE_Y_POS = "\tTurtle Y-Pos:\t";
  public static final String TURTLE_HEADING = "\tTurtle Heading: ";
  public static final String PEN_STATS = "Pen Stats:";
  public static final String PEN_STATE = "\tPen State:\t\t";
  public static final String PEN_COLOR = "\tPen Color:\t";
  public static final String PEN_THICKNESS = "\tThickness:\t";

  private ComboBox<String> myTurtlePicker = new ComboBox<>();
  private ComboBox<String> myTurtleImagePicker = new ComboBox<>();
  private ColorPicker myPenColorPicker = new ColorPicker();
  private GridPane myBonusCommandGrid = new GridPane();
  private VBox myTurtleStats = new VBox();


  //text components
  Label turtleStats = new Label(TURTLE_STATS);
  Label turtleID = new Label(TURTLE_ID);
  Label turtleXPos = new Label(TURTLE_X_POS);
  Label turtleYPos = new Label(TURTLE_Y_POS);
  Label turtleHeading = new Label(TURTLE_HEADING);
  Label penStats = new Label(PEN_STATS);
  Label penState = new Label(PEN_STATE);
  Label penColor = new Label(PEN_COLOR);
  Label penThickness = new Label(PEN_THICKNESS);

  private SlogoView myViewer;

  private int mySelectedTurtleID = 0;

  private ObservableList<String> myTurtleIDs = FXCollections.observableArrayList();
  private Map<Integer, VisualTurtle> myTurtles = new HashMap<>();

  private VBox myOrganizer;

  public TurtleOptionsTab(SlogoView viewer) {
    super(TAB_NAME);
    myViewer = viewer;
    buildTab();
  }

  private void buildTab() {
    myOrganizer = new VBox();
    myOrganizer.getStyleClass().add("vBox");
    for (TurtleImage value : TurtleImage.values()){
      myTurtleImagePicker.getItems().add(value.getName());
    }
    createBonusCommandGrid();
    createTurtleStatsBox();
    initializeButtons();

    myTurtlePicker.itemsProperty().bind(new SimpleObjectProperty<>(myTurtleIDs));
    myTurtlePicker.setOnAction(t -> changeTurtleStats(myTurtlePicker.getValue()));

    myOrganizer.getChildren().addAll(
        new Text("Select a Turtle:"),
        myTurtlePicker,
        new Separator(),
        new Text("Turtle Image:"),
        myTurtleImagePicker,
        new Text("Pen Color:"),
        myPenColorPicker,
        new Separator(),
        new Text("Visual Turtle Movement:"),
        myBonusCommandGrid,
        new Separator(),
        new Text("Current Turtle Statistics"),
        myTurtleStats
    );
    setContent(myOrganizer);
  }

  private void changeTurtleStats(String value) {
    VisualTurtle targetTurtle = myTurtles.get(Integer.parseInt(value));

    turtleID.setText(TURTLE_ID + targetTurtle.getId());
    turtleXPos.setText(TURTLE_X_POS + targetTurtle.getCenterX());
    turtleYPos.setText(TURTLE_Y_POS + targetTurtle.getCenterY());
    turtleHeading.setText(TURTLE_HEADING + targetTurtle.getHeading());

    //penState.setText(penState.getText() + targetTurtle.getPenState());
    penState.setText(PEN_STATE + "DOWN");
    penColor.setText(PEN_COLOR + targetTurtle.getColor());
    penThickness.setText(PEN_THICKNESS + targetTurtle.getPenThickeness());
  }

  private void createTurtleStatsBox() {

    myTurtleStats.getChildren().addAll(turtleStats, turtleID, turtleXPos, turtleYPos,
        turtleHeading, penStats, penState, penColor, penThickness);

  }

  private void createBonusCommandGrid() {

    Button forwardButton = new Button("Forward");
    forwardButton.setOnMouseClicked(e -> myViewer.sendUserCommand("forward 50"));
    Button backButton = new Button("Back");
    backButton.setOnMouseClicked(e -> myViewer.sendUserCommand("back 50"));
    Button rightButton = new Button("Turn Right");
    rightButton.setOnMouseClicked(e -> myViewer.sendUserCommand("right 90"));
    Button leftButton = new Button("Turn Left");
    leftButton.setOnMouseClicked(e -> myViewer.sendUserCommand("left 90"));
    Button resetButton = new Button("Reset");
    resetButton.setOnMouseClicked(e -> myViewer.sendUserCommand("reset"));

    GridPane.setHalignment(resetButton, HPos.CENTER);
    GridPane.setHalignment(backButton, HPos.CENTER);

    myBonusCommandGrid.setAlignment(Pos.CENTER);
    myBonusCommandGrid.setVgap(5);

    myBonusCommandGrid.add(forwardButton, 1, 0);
    myBonusCommandGrid.add(backButton, 1, 2);
    myBonusCommandGrid.add(rightButton, 2, 1);
    myBonusCommandGrid.add(leftButton, 0, 1);
    myBonusCommandGrid.add(resetButton, 1, 1);
  }

  private void initializeButtons() {
    setDefaultTurtleImage();
    myTurtleImagePicker.getSelectionModel().selectedItemProperty().addListener((options, oldValue,
        newValue) -> myViewer.changeTurtleImage(mySelectedTurtleID, newValue));
    myPenColorPicker.setOnAction(t -> {
      Color c = myPenColorPicker.getValue();
      myViewer.setPenColor(c.getRed(), c.getGreen(), c.getBlue());
    });
  }

  private void setDefaultTurtleImage() {
    myTurtleImagePicker.setValue(DEFAULT_TURTLE_IMAGE);
  }

  public void addTurtle(VisualTurtle turtle) {

    if (myTurtles.containsKey(turtle.getId())) {
      myTurtles.replace(turtle.getId(), turtle);
    } else {
      myTurtles.put(turtle.getId(), turtle);
    }
    if (myTurtleIDs.contains(turtle.getId().toString())){
      myTurtleIDs.add(turtle.getId().toString());
    }
    myTurtleIDs.add(turtle.getId().toString());
    if (myTurtlePicker.getValue() != null) {
      changeTurtleStats(myTurtlePicker.getValue());
    }
  }
}
