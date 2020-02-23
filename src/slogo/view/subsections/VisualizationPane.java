package slogo.view.subsections;

import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import slogo.visualcontroller.VisualTurtle;

public class VisualizationPane implements SubPane {

  private ArrayList<VisualTurtle> myTurtles = new ArrayList<>();

  @Override
  public GridPane getNode() {
    GridPane visualizer = new GridPane();

    for (VisualTurtle turtle : myTurtles) {
      ImageView turtleImage = new ImageView(turtle.getImage());
      turtleImage.setFitWidth(turtle.getSize());
      turtleImage.setPreserveRatio(true);
      turtleImage.setRotate(turtle.getHeading());
      turtleImage.setX(turtle.getCenterX());
      turtleImage.setY(turtle.getCenterY());

      Lighting lighting = getLightingEffect(turtle.getColor());
      turtleImage.setEffect(lighting);

      visualizer.getChildren().add(turtleImage);
    }

    visualizer.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
    visualizer.setAlignment(Pos.CENTER);

    return visualizer;
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
