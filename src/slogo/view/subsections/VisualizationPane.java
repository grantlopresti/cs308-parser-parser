package slogo.view.subsections;

import java.util.ArrayList;
import javafx.scene.Group;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import slogo.visualcontroller.VisualTurtle;

public class VisualizationPane implements SubPane {

  private double groupWidth;
  private double groupHeight;

  private ArrayList<VisualTurtle> myTurtles = new ArrayList<>();

  public VisualizationPane(double width, double height){
    groupWidth = width;
    groupHeight = height;
  }

  @Override
  public Group getNode() {
    Group visualizer = new Group();

    setBackground(visualizer);

    for (VisualTurtle turtle : myTurtles) {
      ImageView turtleImage = new ImageView(turtle.getImage());
      turtleImage.setFitWidth(turtle.getSize());
      turtleImage.setPreserveRatio(true);
      turtleImage.setRotate(turtle.getHeading());
      setAdjustedX(turtleImage, turtle.getCenterX());
      setAdjustedY(turtleImage, turtle.getCenterY());

      Lighting lighting = getLightingEffect(turtle.getColor());
      turtleImage.setEffect(lighting);

      visualizer.getChildren().add(turtleImage);
    }

    visualizer.resize(groupWidth, groupHeight);

    return visualizer;
  }

  private void setBackground(Group visualizer) {
    Rectangle background = new Rectangle();
    background.setWidth(groupWidth);
    background.setHeight(groupHeight);
    background.setFill(Color.DARKGRAY);
    visualizer.getChildren().add(background);
  }

  private void setAdjustedX(ImageView turtleImage, double centerX) {
    double adjustedX = centerX + groupWidth /2;
    if (adjustedX <= groupWidth && adjustedX >= 0) {
      turtleImage.setX(adjustedX);
    } else {
      turtleImage.setVisible(false);
    }
  }

  private void setAdjustedY(ImageView turtleImage, double centerY) {
    double adjustedY = -1*centerY + groupHeight /2;
    turtleImage.setY(adjustedY);
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
}
