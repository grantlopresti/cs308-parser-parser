package slogo.view;

public enum TurtleImage {
  TURTLE ("Turtle", "images/turtle.png"),
  DOG ("Dog", "images/dog.png"),
  ARROW ("Arrow", "images/arrow.png"),
  CIRCLE ("Circle", "images/circle.png"),
  SQUARE ("Square", "images/square.png"),
  STAR ("Star", "images/star.png");

  private final String myName;
  private final String myImagePath;
  TurtleImage(String name, String imagePath) {
    myName = name;
    myImagePath = imagePath;
  }

  @Override
  public String toString() { return myName; }

  public String getName() {return myName;}
  public String getImagePath() {return myImagePath;}

}
