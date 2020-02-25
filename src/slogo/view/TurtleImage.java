package slogo.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public abstract class TurtleImage {
  public static final String TURTLE = "images/turtle.png";
  public static final String DOG = "images/dog.png";
  public static final String ARROW = "images/arrow.png";
  public static final String CIRCLE = "images/circle.png";
  public static final String SQUARE = "images/square.png";
  public static final String STAR = "images/star.png";

  public static ObservableList<String> getLanguages() {
    return FXCollections.observableArrayList(
        "Turtle",
        "Dog",
        "Arrow",
        "Circle",
        "Square",
        "Star"
    );
  }
}
