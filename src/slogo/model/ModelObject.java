package slogo.model;

/**
 * Abstract class that dictates encapsulation and logic for all ModelObjects.
 * @author Alex Xu
 */
public abstract class ModelObject implements ModelInterface{
    private double xCoordinate;
    private double yCoordinate;
    private double heading;
    private int ID;

    /**
     * Default Constructor for all ModelObjects
     */
    public ModelObject(){
        xCoordinate = 0.0;
        yCoordinate = 0.0;
        heading = 0.0;
        ID = 0;
    }

    /**
     * Constructor for ModelObject that takes in a specific ID Value.
     * @param idValue
     */
    public ModelObject(int idValue){
        this();
        ID = idValue;
    }

    /**
     * Overrides method defined in the ModelObject Interface. Returns the X location of the ModelObjects (Center of ModelObjects).
     * This coordinate is not necessarily a direct Pixel location.
     * @return int representing X coordinate in an imaginary plane
     */
    @Override
    public double getX() {
        return xCoordinate;
    }

    /**
     * Overrides method defined in the ModelObject Interface. Returns the Y location of the ModelObjects (Center of ModelObjects).
     * This coordinate is not necessarily a direct Pixel location.
     * @return int representing Y coordinate in an imaginary plane
     */
    @Override
    public double getY() {
        return yCoordinate;
    }

    /**
     * Overrides method defined in the ModelObject Interface. Returns the Heading of the ModelObjects (in degrees)
     * @return double representing the heading value
     */
    @Override
    public double getHeading() {
        return heading;
    }

    /**
     * Overrides method defined in the ModelObject Interface. Moves the ModelObjects by the specified distance.
     * @param distance to move the ModelObjects
     */
    @Override
    public void move(double distance) {
        xCoordinate += calcX(distance);
        yCoordinate += calcY(distance);
    }

    /**
     * Overrides method defined in the ModelObject Interface. Sets the heading of the ModelObjects to the specified degree.
     * @param degree to set the ModelObjects heading to
     */
    @Override
    public void setHeading(double degree) {
        heading = degree;
    }

    /**
     * Overrides method defined in the ModelObject Interface. Turns the ModelObjects (heading) by the specified degree.
     * @param degree to turn the ModelObjects by
     */
    @Override
    public void turn(double degree) {
        heading += degree;
    }

    /**
     * Returns the ID Number of the ModelObject.
     */
    @Override
    public int getID(){
        return ID;
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
