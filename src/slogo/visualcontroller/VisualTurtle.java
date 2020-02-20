package slogo.visualcontroller;

import javafx.scene.image.Image;

public class VisualTurtle extends VisualObject {
  Image myImage;
  double myCenterX;
  double myCenterY;
  double myHeading;

  public Image getImage() {
    return myImage;
  }

  public void setImage(Image image) {
    myImage = image;
  }

  public double getCenterX() {
    return myCenterX;
  }

  public void setCenterX(double centerX) {
    myCenterX = centerX;
  }

  public double getCenterY() {
    return myCenterY;
  }

  public void setCenterY(double centerY) {
    myCenterY = centerY;
  }

  public double getHeading() {
    return myHeading;
  }

  public void setHeading(double heading) {
    myHeading = heading;
  }


}
