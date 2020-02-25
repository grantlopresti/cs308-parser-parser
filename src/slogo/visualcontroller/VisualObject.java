package slogo.visualcontroller;

import java.awt.Color;

// TODO - Currently unimplemented, decide whether or not useful
public abstract class VisualObject {
  Color myColor;

  public void setcolor(Color color) {
    myColor = color;
  }

  public Color getMyColor() {
    return myColor;
  }
}
