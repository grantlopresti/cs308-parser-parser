package slogo.view.subsections;

import java.util.List;
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
import slogo.view.windows.SlogoView;
import slogo.visualcontroller.VisualTurtle;

public class VisualizationPane implements SubPane {

  private List<VisualTurtle> myTurtles;

  @Override
  public GridPane getNode() {
    GridPane visualizer = new GridPane();

    ImageView turtle = new ImageView("resources/images/TurtleBasicWhite.PNG");
    turtle.setFitWidth(40);
    turtle.setPreserveRatio(true);
    turtle.setRotate(-90);
    turtle.setX(0);
    turtle.setY(0);

    Lighting lighting = getLightingEffect(Color.PURPLE);
    turtle.setEffect(lighting);

    visualizer.getChildren().addAll(turtle);

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

  }
}
