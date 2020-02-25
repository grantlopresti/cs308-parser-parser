package slogo.visualcontroller;

import javafx.scene.paint.Color;

public class VisualLine {
  private static final Color DEFAULT_COLOR = Color.BLACK;
  private static final double DEFAULT_THICKNESS = 5;

  private final double myStartX;
  private final double myStartY;
  private final double myEndX;
  private final double myEndY;
  private final Color myColor;
  private final double myThickness;

  public VisualLine(double startX, double startY, double endX, double endY, Color color, double thickness) {
    this.myStartX = startX;
    this.myStartY = startY;
    this.myEndX = endX;
    this.myEndY = endY;
    this.myColor = color;
    this.myThickness = thickness;
  }

  public VisualLine(VisualTurtle turtle) {
    this.myStartX = turtle.getPreviousX();
    this.myEndX = turtle.getCenterX();
    this.myStartY = turtle.getPreviousY();
    this.myEndY = turtle.getCenterY();
    this.myColor = turtle.getColor();
    this.myThickness = turtle.getPenThickeness();
  }

  public double getStartX() {
    return myStartX;
  }

  public double getStartY() {
    return myStartY;
  }

  public double getEndX() {
    return myEndX;
  }

  public double getEndY() {
    return myEndY;
  }

  public Color getColor() {
    return myColor;
  }

  public double getThickness() {
    return myThickness;
  }

}
