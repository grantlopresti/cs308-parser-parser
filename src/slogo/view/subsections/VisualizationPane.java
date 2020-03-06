package slogo.view.subsections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import slogo.view.TurtleImage;
import slogo.visualcontroller.VisualLine;
import slogo.visualcontroller.VisualTurtle;

public class VisualizationPane extends Group {

  private static final Color DEFAULT_BG_COLOR = Color.DARKGRAY;
  private static final Color DEFAULT_PEN_COLOR = Color.BLACK;

  private double groupWidth;
  private double groupHeight;

  private Color myBGColor = DEFAULT_BG_COLOR;
  private Rectangle myBackgroundRect;

  private Map<Integer, VisualTurtle> myTurtles = new HashMap<>();
  private Map<Integer, ImageView> myTurtlesImageViews = new HashMap<>();
  private List<VisualLine> myLines = new ArrayList<>();

  public VisualizationPane(double width, double height){
    super();
    groupWidth = width;
    groupHeight = height;
  }

  public void update() {
    setBackground();
    addTurtlesToVisualizer();
    addLinesToVisualizer();
    resize(groupWidth, groupHeight);
  }

  private void addLinesToVisualizer() {
    for (VisualLine line : myLines) {
      Line lineImage = new Line();

      setAdjustedLineLocations(line, lineImage);

      lineImage.setStroke(line.getColor());
      lineImage.setStrokeWidth(line.getThickness());

      getChildren().add(lineImage);
    }
  }

  private void setAdjustedLineLocations(VisualLine line, Line lineImage) {
    lineImage.setStartX(getAdjustedX(line.getStartX()));
    lineImage.setStartY(getAdjustedY(line.getStartY()));

    lineImage.setEndX(getAdjustedX(line.getEndX()));
    lineImage.setEndY(getAdjustedY(line.getEndY()));
  }

  private void addTurtlesToVisualizer() {
    for (VisualTurtle turtle : myTurtles.values()) {
      getChildren().remove(myTurtlesImageViews.get(turtle.getId()));
      ImageView turtleImage = new ImageView(turtle.getImage().getImagePath());
      myTurtlesImageViews.remove(turtle.getId());
      myTurtlesImageViews.put(turtle.getId(), turtleImage);

      visualizeTurtle(turtleImage, turtle);
    }
  }

  private void visualizeTurtle(ImageView turtleImage, VisualTurtle turtle) {
    turtleImage.setFitWidth(turtle.getSize());
    turtleImage.setPreserveRatio(true);

    turtleImage.setX(getAdjustedX(turtle.getPreviousX())-(turtle.getSize()/2));
    turtleImage.setY(getAdjustedY(turtle.getPreviousY())-(turtle.getSize()/2));

    if (turtle.getPreviousX() != turtle.getCenterX()) {
      Animation turtleMoveAnimation = makeMoveAnimation(turtle, turtleImage);
      turtleMoveAnimation.play();
    }

    turtleImage.rotateProperty().set(360 - turtle.getHeading());

    turtleImage.setX(getAdjustedX(turtle.getCenterX())-(turtle.getSize()/2));
    turtleImage.setY(getAdjustedY(turtle.getCenterY())-(turtle.getSize()/2));

    System.out.println();

    Lighting lighting = getLightingEffect(turtle.getColor());
    turtleImage.setEffect(lighting);

    getChildren().add(turtleImage);
  }

  private Animation makeMoveAnimation (VisualTurtle turtle, Node agent) {
    // create something to follow
    Path path = new Path();
    path.getElements().add(new MoveTo(getAdjustedX(turtle.getPreviousX()),
        getAdjustedY(turtle.getPreviousY())));
    path.getElements().add(new LineTo(getAdjustedX(turtle.getCenterX()),
        getAdjustedY(turtle.getCenterY())));
    // create an animation where the shape follows a path
    PathTransition pt = new PathTransition(Duration.seconds(3), path, agent);
    // put them together in order
    return new SequentialTransition(agent, pt);
  }

//  private Animation makeTurnAnimation (VisualTurtle turtle, Node agent) {
//    // create an animation that rotates the shape
//    RotateTransition rt = new RotateTransition(Duration.seconds(3));
//    rt.setFromAngle(360 - turtle.getPreviousHeading());
//    rt.setToAngle(360 - turtle.getHeading());
//
//    return new SequentialTransition(agent, rt);
//  }
  
  private void setBackground() {
    myBackgroundRect = new Rectangle();
    myBackgroundRect.setWidth(groupWidth);
    myBackgroundRect.setHeight(groupHeight);
    myBackgroundRect.setFill(myBGColor);
    getChildren().add(myBackgroundRect);
  }

  private double getAdjustedX(double centerX) {
    return centerX + groupWidth /2;
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
    myTurtles.remove(turtle.getId());
    myTurtles.put(turtle.getId(), turtle);
    update();
  }

  public void addVisualLine(VisualLine line) {
    myLines.add(line);
    update();
  }

  public void setBGColor(double red, double green, double blue) {
    myBGColor = new Color(red, green, blue, 1);
    myBackgroundRect.setFill(myBGColor);
  }

  public void clearElements() {
    myTurtles = new HashMap<>();
    myLines = new ArrayList<>();
    update();
  }

  public void resetBGColor() {
    myBGColor = DEFAULT_BG_COLOR;
    myBackgroundRect.setFill(myBGColor);
  }

  public void changeTurtleImage(int ID, String imageName) {
    VisualTurtle targetTurtle = myTurtles.get(ID);

    targetTurtle.setImage(TurtleImage.valueOf(imageName));

    visualizeTurtle(new ImageView(targetTurtle.getImage().getImagePath()), targetTurtle);
  }

  public void showTurtleInCenter() {
    visualizeTurtle(new ImageView(TurtleImage.TURTLE.getImagePath()), new VisualTurtle());
  }
}
