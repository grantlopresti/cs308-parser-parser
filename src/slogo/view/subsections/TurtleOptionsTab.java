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
    Label tabTitleLine = new Label("Turtle Options");
    Label instructions = new Label("Choose a Turtle Below to see its characteristics");
    for (TurtleImage value : TurtleImage.values()){
      myTurtleImagePicker.getItems().add(value.getName());
    }
    createBonusCommandGrid();
    initializeButtons();
    myTurtlePicker.itemsProperty().bind(myController.getMyTurtlesProperty());
    myOrganizer.getChildren().addAll(
        tabTitleLine,
        instructions,
        new Separator(),
        new Text("Select a Turtle:"),
        new ComboBox<>(),
        new Separator(),
        new Text("Turtle Image:"),
        myTurtleImagePicker,
        new Text("Pen Color:"),
        myPenColorPicker,
        new Separator(),
        new Text("Visual Turtle Movement:"),
        myBonusCommandGrid
    );
    setContent(myOrganizer);
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
