package slogo.visualcontroller;

import java.awt.Color;

public abstract class VisualObject {
  Color myColor;

  public void setcolor(Color color) {
    myColor = color;
  }

  public Color getMyColor() {
    return myColor;
  }
}
