package slogo.view.subsections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.animation.Animation;
import javafx.animation.PathTransition;
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
  public static final int FULL_ROTATION = 360;
  public static final int EDGE_BUFFER = 5;
  //private static final Color DEFAULT_PEN_COLOR = Color.BLACK;

  private double groupWidth;
  private double groupHeight;

  private Color myBGColor = DEFAULT_BG_COLOR;
  private Rectangle myBackgroundRect = new Rectangle();

  private Map<Integer, VisualTurtle> myTurtles = new HashMap<>();
  private Map<Integer, ImageView> myTurtlesImageViews = new HashMap<>();
  private List<VisualLine> myLines = new ArrayList<>();
  private List<Line> myLinesOnScreen = new ArrayList<>();

  private static int ANIMATION_RATE;

  public VisualizationPane(double width, double height, int rate){
    super();
    groupWidth = width;
    groupHeight = height;
    ANIMATION_RATE = rate;
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

      myLinesOnScreen.add(lineImage);
      getChildren().add(lineImage);
    }
  }

  private void setAdjustedLineLocations(VisualLine line, Line lineImage) {
    lineImage.setStartX(getAdjustedX(getInbounds(line.getStartX(), line.getThickness(),
        groupWidth)));
    lineImage.setStartY(getAdjustedY(getInbounds(line.getStartY(), line.getThickness(),
        groupHeight)));

    lineImage.setEndX(getAdjustedX(getInbounds(line.getEndX(), line.getThickness(), groupWidth)));
    lineImage.setEndY(getAdjustedY(getInbounds(line.getEndY(), line.getThickness(), groupHeight)));
  }

  private void addTurtlesToVisualizer() {
    for (VisualTurtle turtle : myTurtles.values()) {
      getChildren().remove(myTurtlesImageViews.get(turtle.getId()));
      ImageView turtleImage = new ImageView(turtle.getImage().getImagePath());
      myTurtlesImageViews.remove(turtle.getId());
      myTurtlesImageViews.put(turtle.getId(), turtleImage);

      visualizeTurtle(turtleImage, turtle, true);
    }
  }

  private void visualizeTurtle(ImageView turtleImage, VisualTurtle turtle, Boolean doAnimate) {
    turtleImage.setFitWidth(turtle.getSize());
    turtleImage.setPreserveRatio(true);

    animateIfNeeded(turtleImage, turtle, doAnimate);
    turtleImage.rotateProperty().set(FULL_ROTATION - turtle.getHeading());
    setFinalPosition(turtleImage, turtle);

    Lighting lighting = getLightingEffect(turtle.getColor());
    turtleImage.setEffect(lighting);

    getChildren().add(turtleImage);
  }

  private void setFinalPosition(ImageView turtleImage, VisualTurtle turtle) {
    turtleImage.setX(getAdjustedX(getInbounds(turtle.getCenterX(),
        turtleImage.getFitWidth(), groupWidth))- (turtle.getSize()/2));
    turtleImage.setY(getAdjustedY(getInbounds(turtle.getCenterY(),
        turtleImage.getFitHeight(), groupHeight))- (turtle.getSize()/2));
  }

  private void animateIfNeeded(ImageView turtleImage, VisualTurtle turtle, Boolean doAnimate) {
    if (doAnimate && turtle.getPreviousX() != turtle.getCenterX()) {
      Animation turtleMoveAnimation = makeMoveAnimation(turtle, turtleImage);
      turtleMoveAnimation.play();
    }
  }

  private double getInbounds(double coordinate, double imageSize, double dimension) {
    double maxDistance = dimension/2;
    double imageAdjustment = imageSize/2;
    double ret;
    if (coordinate < (-1 * maxDistance)+imageAdjustment){
      ret = -1 * maxDistance + imageAdjustment + EDGE_BUFFER;
    } else if (coordinate > maxDistance - imageAdjustment){
      ret = maxDistance - imageAdjustment - EDGE_BUFFER;
    } else {
      ret = coordinate;
    }
    return ret;
  }

  private Animation makeMoveAnimation (VisualTurtle turtle, Node agent) {
    // create something to follow
    Path path = new Path();
    path.getElements().add(new MoveTo(
        getAdjustedX(getInbounds(turtle.getPreviousX(), turtle.getSize(), groupWidth)),
        getAdjustedY(getInbounds(turtle.getPreviousY(), turtle.getSize(), groupHeight))
    ));
    double nextX = getAdjustedX(getInbounds(turtle.getCenterX(), turtle.getSize(), groupWidth));
    double nextY = getAdjustedY(getInbounds(turtle.getCenterY(), turtle.getSize(), groupHeight));
    path.getElements().add(new LineTo(nextX,nextY));
    // create an animation where the shape follows a path
    PathTransition pt = new PathTransition(Duration.seconds(ANIMATION_RATE), path, agent);
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
    myBackgroundRect.setWidth(groupWidth);
    myBackgroundRect.setHeight(groupHeight);
    myBackgroundRect.setFill(myBGColor);
    if (getChildren().isEmpty()){
      getChildren().add(myBackgroundRect);
    }
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
    removeTurtles();
    removeLines();
    myTurtles = new HashMap<>();
    myTurtlesImageViews = new HashMap<>();
    myLines = new ArrayList<>();
    myLinesOnScreen = new ArrayList<>();
    update();
  }

  private void removeLines() {
    for (Line line : myLinesOnScreen){
      getChildren().remove(line);
    }
  }

  private void removeTurtles() {
    for (ImageView turtle : myTurtlesImageViews.values()){
      getChildren().remove(turtle);
    }
  }

  public void resetBGColor() {
    myBGColor = DEFAULT_BG_COLOR;
    myBackgroundRect.setFill(myBGColor);
  }

  public void changeTurtleImage(int ID, String imageName) {
    VisualTurtle targetTurtle = myTurtles.get(ID);

    removeOldImage(ID);

    ImageView newImage = replaceWithNewImage(ID, imageName, targetTurtle);

    visualizeTurtle(newImage, targetTurtle, false);
  }

  private ImageView replaceWithNewImage(int ID, String imageName, VisualTurtle targetTurtle) {
    targetTurtle.setImage(TurtleImage.valueOf(imageName));
    ImageView newImage = new ImageView(targetTurtle.getImage().getImagePath());
    myTurtlesImageViews.put(ID, newImage);
    return newImage;
  }

  private void removeOldImage(int ID) {
    if (myTurtlesImageViews.values().size() > 0) {
      getChildren().remove(myTurtlesImageViews.get(ID));
      myTurtlesImageViews.remove(ID);
    }
  }

}
