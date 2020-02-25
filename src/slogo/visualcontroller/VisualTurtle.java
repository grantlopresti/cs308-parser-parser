package slogo.visualcontroller;

import javafx.scene.paint.Color;
import slogo.model.ModelTurtle;
import slogo.view.TurtleImage;

public class VisualTurtle extends VisualObject {

  public static final boolean DEFAULT_CHANGE_STATE = false;
  public static final String DEFAULT_IMAGE = TurtleImage.TURTLE;
  public static final Color DEFAULT_COLOR = Color.PURPLE;
  public static final double DEFAULT_SIZE = 30;
  public static final double DEFAULT_X = 0;
  public static final double DEFAULT_Y = 0;
  public static final double DEFAULT_HEADING = 0;

  private int myID;
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
  private double myPenThickness;

  /**
   * Empty constructor for testing within VisualizationPane
   */
  public VisualTurtle(){

  }

  /**
   * Constructor for visual turtle object from a model turtle object
   * @param turtle is a model turtle, from the visual controller (via logical controller)
   *               that is constructed here for model manipulation
   */
  public VisualTurtle(ModelTurtle turtle) {
    setCurrentValues(turtle);
    this.myID = turtle.getID();
  }

  /**
   * Called on VisualTurtle in VisualController to modify turtle state
   * @param turtle
   * TODO - Only pass around immutable turtle objects
   */
  public void updateVisualTurtle(ModelTurtle turtle) {
    if (turtle.getID() == myID) {
      updatePriorValues(turtle);
      setCurrentValues(turtle);
    } else {
      // TODO - implement error condition, visual controller attempting to modify wrong turtle
    }

  }

  private void setCurrentValues(ModelTurtle turtle) {
    this.myCenterX = turtle.getX();
    this.myCenterY = turtle.getY();
    this.myHeading = turtle.getHeading();
    this.myPenThickness = turtle.getPenThickness();
  }

  private void updatePriorValues(ModelTurtle turtle) {
    this.myPreviousX = this.myCenterX;
    this.myPreviousY = this.myCenterY;
    this.myPreviousHeading = this.myHeading;
  }

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

  public double getPenThickeness() {
    return myPenThickness;
  }

}
