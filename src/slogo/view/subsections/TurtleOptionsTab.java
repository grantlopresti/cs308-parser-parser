package slogo.view.subsections;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import slogo.view.TurtleImage;
import slogo.view.windows.SlogoView;
import slogo.visualcontroller.VisualController;

public class TurtleOptionsTab extends Tab {

  public static final String TAB_NAME = "Turtle Options";

  private static final String DEFAULT_TURTLE_IMAGE = "Turtle";

  private ComboBox<String> myTurtlePicker = new ComboBox<>();
  private ComboBox<String> myTurtleImagePicker = new ComboBox<>();
  private ColorPicker myPenColorPicker = new ColorPicker();
  private GridPane myBonusCommandGrid = new GridPane();
  private VBox myTurtleStats = new VBox();

  private VisualController myController;
  private SlogoView myViewer;

  private int mySelectedTurtleID = 0;

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
    for (TurtleImage value : TurtleImage.values()){
      myTurtleImagePicker.getItems().add(value.getName());
    }
    createBonusCommandGrid();
    createTurtleStatsBox();
    initializeButtons();

    myTurtlePicker.itemsProperty().bind(myController.getMyTurtlesProperty());

    myOrganizer.getChildren().addAll(
        new Text("Select a Turtle:"),
        new ComboBox<>(),
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

  private void createTurtleStatsBox() {

    int boundTurtleID = 0;
    double boundTurtleXPosition = 0;
    double boundTurtleYPosition = 0;
    double boundTurtleHeading = 0;
    int boundPenState = 0;
    String boundPenColor = "#022822";
    double boundPenThickness = 0;

    // position, heading) and the pen (i.e., up/down, color, thickness
    Label turtleStats = new Label("Turtle Stats:");
    Label turtleID = new Label("\tTurtle ID:\t\t" + boundTurtleID);
    Label turtleXPos = new Label("\tTurtle X-Pos:\t" + boundTurtleXPosition);
    Label turtleYPos = new Label("\tTurtle Y-Pos:\t" + boundTurtleYPosition);
    Label turtleHeading = new Label("\tTurtle Heading: " + boundTurtleHeading);
    Label penStats = new Label("Pen Stats:");
    Label penState = new Label("\tPen State:\t\t" + boundPenState);
    Label penColor = new Label("\tPen Color:\t" + boundPenColor);
    Label penThickness = new Label("\tThickness:\t" + boundPenThickness);

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
}
