package slogo.model;

/**
 * Interface/API for Immutable Model Objects.
 * @author  Alex Xu
 */
public interface ImmutableObjectInterface {
    /**
     * Returns the X location of the ModelObject, depending on implementation
     * @return double representing X coordinate
     */
    public double getX();

    /**
     * Returns the Y location of the ModelObject, depending on the implementation
     * @return double representing Y coordinate
     */
    public double getY();

    /**
     * Returns heading (in degrees) of the ModelObject
     * @return double representing degrees
     */
    public double getHeading();

    /**
     * Returns the ID of the ModelObject
     * @return int representing the ID number
     */
    public int getID();

    /**
     * Returns whether the Pen is Active or Not.
     * @return boolean representing pen state.
     */
    public boolean isPenActive();

    /**
     * Returns the thickness of the pen
     * @return double representing the thickness of the Pen.
     */
    public double getPenThickness();
}
