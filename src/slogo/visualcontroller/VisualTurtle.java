package slogo.visualcontroller;

import javafx.scene.paint.Color;
import slogo.view.TurtleImage;

public class VisualTurtle extends VisualObject {

  public static final boolean DEFAULT_CHANGE_STATE = false;
  public static final String DEFAULT_IMAGE = TurtleImage.TURTLE;
  public static final Color DEFAULT_COLOR = Color.PURPLE;
  public static final double DEFAULT_SIZE = 30;
  public static final double DEFAULT_X = 0;
  public static final double DEFAULT_Y = 0;
  public static final double DEFAULT_HEADING = 0;

  private boolean myChangeState = DEFAULT_CHANGE_STATE;
  private String myImage = DEFAULT_IMAGE;
  private Color myColor = DEFAULT_COLOR;
  private double myCenterX = DEFAULT_X;
  private double myCenterY = DEFAULT_Y;
  private double myPreviousX = DEFAULT_X;
  private double myPreviousY = DEFAULT_Y;
  private double myHeading = DEFAULT_HEADING;
  private double myPreviousHeading = DEFAULT_HEADING;
  private double mySize = DEFAULT_SIZE;

  public double getSize() {
    return mySize;
  }

  public void setSize(double size) {
    mySize = size;
  }

  public boolean hasChangedState() {
    return myChangeState;
  }

  public void setChangeState(boolean changeState) {
    myChangeState = changeState;
  }

  public String getImage() {
    return myImage;
  }

  public void setImage(String image) {
    myImage = image;
  }

  public double getCenterX() {
    return myCenterX;
  }

  public void setCenterX(double centerX) {
    myCenterX = centerX;
  }

  public double getCenterY() {
    return myCenterY ;
  }

  public void setCenterY(double centerY) {
    myCenterY = centerY;
  }

  public double getPreviousX() {
    return myPreviousX;
  }

  public void setPreviousX(double previousX) {
    myPreviousX = previousX;
  }

  public double getPreviousY() {
    return myPreviousY;
  }

  public void setPreviousY(double previousY) {
    myPreviousY = previousY;
  }

  public double getHeading() {
    return myHeading;
  }

  public double getPreviousHeading() {
    return myPreviousHeading;
  }

  public void setHeading(double heading) {
    myHeading = heading;
  }

  public Color getColor() {
    return myColor;
  }

  public void setColor(Color color) {
    myColor = color;
  }

  public void setCenter(double centerX, double centerY) {
    myCenterX = centerX;
    myCenterY = centerY;
  }

}
