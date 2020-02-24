package slogo.visualcontroller;

import javafx.scene.paint.Color;

public class VisualLine {
  private static final Color DEFAULT_COLOR = Color.BLACK;
  private static final double DEFAULT_THICKNESS = 5;

  private double myStartX;
  private double myStartY;
  private double myEndX;
  private double myEndY;
  private Color myColor = DEFAULT_COLOR;
  private double myThickness = DEFAULT_THICKNESS;

  public double getStartX() {
    return myStartX;
  }

  public void setStartX(double startX) {
    myStartX = startX;
  }

  public double getStartY() {
    return myStartY;
  }

  public void setStartY(double startY) {
    myStartY = startY;
  }

  public double getEndX() {
    return myEndX;
  }

  public void setEndX(double endX) {
    myEndX = endX;
  }

  public double getEndY() {
    return myEndY;
  }

  public void setEndY(double endY) {
    myEndY = endY;
  }

  public Color getColor() {
    return myColor;
  }

  public void setColor(Color color) {
    myColor = color;
  }

  public double getThickness() {
    return myThickness;
  }

  public void setThickness(double thickness) {
    myThickness = thickness;
  }
  
  
}
