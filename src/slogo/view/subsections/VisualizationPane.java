package slogo.view.subsections;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.Group;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import slogo.visualcontroller.VisualCommand;
import slogo.visualcontroller.VisualData;
import slogo.visualcontroller.VisualError;
import slogo.visualcontroller.VisualLine;
import slogo.visualcontroller.VisualTurtle;
import slogo.visualcontroller.VisualUserFunction;

public class VisualizationPane implements SubPane {

  private double groupWidth;
  private double groupHeight;

  private Color myBGColor = Color.DARKGRAY;

  private List<VisualTurtle> myTurtles = new ArrayList<>();
  private List<VisualCommand> myCommands = new ArrayList<>();
  private List<VisualError> myErrors = new ArrayList<>();
  private List<VisualUserFunction> myFunctions = new ArrayList<>();
  private List<VisualLine> myLines = new ArrayList<>();
  private List<VisualData> myData = new ArrayList<>();

  public VisualizationPane(double width, double height){
    groupWidth = width;
    groupHeight = height;
  }

  @Override
  public Group getNode() {
    Group visualizer = new Group();

    setBackground(visualizer);

    addTurtlesToVisualizer(visualizer);
    addLinesToVisualizer(visualizer);

    visualizer.resize(groupWidth, groupHeight);

    return visualizer;
  }

  private void addLinesToVisualizer(Group visualizer) {
    for (VisualLine line : myLines) {
      Line lineImage = new Line();

      setAdjustedLineLocations(line, lineImage);

      lineImage.setStroke(line.getColor());
      lineImage.setStrokeWidth(line.getThickness());

      visualizer.getChildren().add(lineImage);
    }
  }

  private void setAdjustedLineLocations(VisualLine line, Line lineImage) {
    lineImage.setStartX(line.getStartX());
    lineImage.setStartY(line.getStartY());

    lineImage.setEndX(line.getEndX());
    lineImage.setEndY(line.getEndY());
  }

  private void addTurtlesToVisualizer(Group visualizer) {
    for (VisualTurtle turtle : myTurtles) {
      ImageView turtleImage = new ImageView(turtle.getImage());

      turtleImage.setFitWidth(turtle.getSize());
      turtleImage.setPreserveRatio(true);
      turtleImage.setRotate(turtle.getHeading());

      setAdjustedX(turtleImage, turtle.getCenterX());
      setAdjustedY(turtleImage, turtle.getCenterY());

      Lighting lighting = getLightingEffect(turtle.getColor());
      turtleImage.setEffect(lighting);

      visualizer.getChildren().add(turtleImage);
    }
  }

  private void setBackground(Group visualizer) {
    Rectangle background = new Rectangle();
    background.setWidth(groupWidth);
    background.setHeight(groupHeight);
    background.setFill(myBGColor);
    visualizer.getChildren().add(background);
  }

  private void setAdjustedX(ImageView turtleImage, double centerX) {
    double adjustedX = centerX + groupWidth /2;
    if (adjustedX <= groupWidth && adjustedX >= 0) {
      turtleImage.setX(adjustedX);
    } else {
      turtleImage.setVisible(false);
    }
  }

  private void setAdjustedY(ImageView turtleImage, double centerY) {
    double adjustedY = -1*centerY + groupHeight /2;
    turtleImage.setY(adjustedY);
  }

  private Lighting getLightingEffect (Color color) {
    Lighting lighting = new Lighting();
    lighting.setDiffuseConstant(1.0);
    lighting.setSpecularConstant(0.0);
    lighting.setSpecularExponent(0.0);
    lighting.setSurfaceScale(0.0);
    lighting.setLight(new Light.Distant(45, 45, color));
    return lighting;
  }

  public void addVisualTurtle(VisualTurtle turtle) {
    myTurtles.add(turtle);
  }

  public void addVisualCommand(VisualCommand command) {
    myCommands.add(command);
  }

  public void addVisualLine(VisualLine line) {
    myLines.add(line);
  }

  public void addVisualError(VisualError error) {
    myErrors.add(error);
    displayError(error);
  }

  private void displayError(VisualError error) {
    //TODO: add code to have popup that displays error message with okay button
  }

  public void addVisualData(VisualData data) {
    myData.add(data);
  }

  public void addVisualUserFunction(VisualUserFunction function) {
    myFunctions.add(function);
  }

  public void setBGColor(double red, double green, double blue) {
    myBGColor = new Color(red, green, blue, 1);
  }
}
