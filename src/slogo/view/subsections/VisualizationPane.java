package slogo.view.subsections;

import java.util.ArrayList;
import java.util.List;
import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.HLineTo;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import slogo.visualcontroller.VisualCommand;
import slogo.visualcontroller.VisualData;
import slogo.visualcontroller.VisualError;
import slogo.visualcontroller.VisualLine;
import slogo.visualcontroller.VisualTurtle;
import slogo.visualcontroller.VisualUserFunction;

public class VisualizationPane implements SubPane {

  private static final Color DEFAULT_COLOR = Color.DARKGRAY;

  private double groupWidth;
  private double groupHeight;

  private Color myBGColor = DEFAULT_COLOR;

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
      //turtleImage.setRotate(turtle.getHeading());

      setAdjustedX(turtleImage, turtle.getPreviousX());
      setAdjustedY(turtleImage, turtle.getPreviousY());

      if (turtle.hasChangedState()) {
        Animation turtleAnimation = makeAnimation(turtle, turtleImage);
        turtleAnimation.play();
      } else {
        setAdjustedX(turtleImage, turtle.getCenterX());
        setAdjustedY(turtleImage, turtle.getCenterY());
      }

      Lighting lighting = getLightingEffect(turtle.getColor());
      turtleImage.setEffect(lighting);

      visualizer.getChildren().add(turtleImage);
    }
  }

  private Animation makeAnimation (VisualTurtle turtle, Node agent) {
    // create something to follow
    Path path = new Path();
    path.getElements().add(new MoveTo(getAdjustedX(turtle.getPreviousX()),
        getAdjustedY(turtle.getPreviousY())));
    path.getElements().add(new LineTo(getAdjustedX(turtle.getCenterX()),
        getAdjustedY(turtle.getCenterY())));
    // create an animation where the shape follows a path
    PathTransition pt = new PathTransition(Duration.seconds(4), path, agent);
    // create an animation that rotates the shape
    RotateTransition rt = new RotateTransition(Duration.seconds(3));
    rt.setByAngle(turtle.getPreviousHeading()-turtle.getHeading());
    // put them together in order
    return new SequentialTransition(agent, pt, rt);
  }

  private void setBackground(Group visualizer) {
    Rectangle background = new Rectangle();
    background.setWidth(groupWidth);
    background.setHeight(groupHeight);
    background.setFill(myBGColor);
    visualizer.getChildren().add(background);
  }

  private void setAdjustedX(ImageView turtleImage, double centerX) {
    double adjustedX = getAdjustedX(centerX);
    if (adjustedX <= groupWidth && adjustedX >= 0) {
      turtleImage.setX(adjustedX);
    } else {
      turtleImage.setVisible(false);
    }
  }

  private double getAdjustedX(double centerX) {
    return centerX + groupWidth /2;
  }

  private void setAdjustedY(ImageView turtleImage, double centerY) {
    double adjustedY = getAdjustedY(centerY);
    turtleImage.setY(adjustedY);
  }

  private double getAdjustedY(double centerY) {
    return -1*centerY + groupHeight /2;
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

  public void clearElements() {
    myTurtles = new ArrayList<>();
    myLines = new ArrayList<>();
  }

  public void resetBGColor() {
    myBGColor = DEFAULT_COLOR;
  }
}
