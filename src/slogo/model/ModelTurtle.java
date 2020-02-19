package slogo.model;

/**
 * Class handles encapsulation of Model Turtle information and movement logic.
 * @author Alex Xu
 */
public class ModelTurtle implements ModelObject{

    private double xCoordinate;
    private double yCoordinate;
    private double heading;

    public ModelTurtle(){
        xCoordinate = 0.0;
        yCoordinate = 0.0;
        heading = 0.0;
    }

    /**
     * Overrides method defined in the ModelObject Interface. Returns the X location of the turtle (Center of turtle).
     * This coordinate is not necessarily a direct Pixel location.
     * @return int representing X coordinate in an imaginary plane
     */
    @Override
    public double getX() {
        return xCoordinate;
    }

    /**
     * Overrides method defined in the ModelObject Interface. Returns the Y location of the turtle (Center of turtle).
     * This coordinate is not necessarily a direct Pixel location.
     * @return int representing Y coordinate in an imaginary plane
     */
    @Override
    public double getY() {
        return yCoordinate;
    }

    /**
     * Overrides method defined in the ModelObject Interface. Returns the Heading of the turtle (in degrees)
     * @return double representing the heading value
     */
    @Override
    public double getHeading() {
        return heading;
    }

    /**
     * Overrides method defined in the ModelObject Interface. Moves the Turtle by the specified distance.
     * @param distance to move the Turtle
     */
    @Override
    public void move(double distance) {
        xCoordinate += calcX(distance);
        yCoordinate += calcY(distance);
    }

    /**
     * Overrides method defined in the ModelObject Interface. Sets the heading of the Turtle to the specified degree.
     * @param degree to set the Turtle heading to
     */
    @Override
    public void setHeading(double degree) {
        heading = degree;
    }

    /**
     * Overrides method defined in the ModelObject Interface. Turns the turtle (heading) by the specified degree.
     * @param degree to turn the Turtle by
     */
    @Override
    public void turn(double degree) {
        heading += degree;
    }

    private double calcX(double distance){
        double radians = Math.toRadians(heading);
        double cosValue = Math.cos(radians);
        return distance*cosValue;
    }

    private double calcY(double distance){
        double radians = Math.toRadians(heading);
        double sinValue = Math.sin(radians);
        return distance*sinValue;
    }


}
