package slogo.view;

/**
 * FOR MASTERPIECE:
 *
 * Enum Turtle Image is a simple class that is referenced throughout the project and allows the
 * user to choose one of a set number of images for the turtle object. This is essentially the
 * template framework for all of the potential palettes and choices the user has for changing
 * turtle attributes.
 *
 * @author Grant LoPresti
 */
public enum TurtleImage {
  TURTLE ("Turtle", "images/turtle.png"),
  DOG ("Dog", "images/dog.png"),
  ARROW ("Arrow", "images/arrow.png"),
  CIRCLE ("Circle", "images/circle.png"),
  SQUARE ("Square", "images/square.png"),
  STAR ("Star", "images/star.png");

  private final String myName;
  private final String myImagePath;

  /**
   * Basic format for the TurtleImage enum
   * @param name display name of the image
   * @param imagePath file path of the image file
   *
   * @author Grant LoPresti
   */
  TurtleImage(String name, String imagePath) {
    myName = name;
    myImagePath = imagePath;
  }

  /**
   * Simply changes the toString method to return the display name
   * @return the display name of the specific turtle image
   *
   * @author Grant LoPresti
   */
  @Override
  public String toString() { return myName; }

  /**
   * Gets the display name
   * @return display name
   *
   * @author Grant LoPresti
   */
  public String getName() {return myName;}

  /**
   * Gets the image path
   * @return image path
   *
   * @author Grant LoPresti
   */
  public String getImagePath() {return myImagePath;}

}
