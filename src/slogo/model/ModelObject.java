package slogo.model;

/**
 * Interface that dictates public methods that all ModelObjects are expected to support.
 * @author Alex Xu
 */
public interface ModelObject{

    /**
     * Returns the X location of the ModelObject, depending on implementation
     * @return integer representing X coordinate
     */
    public int getX();

    /**
     * Returns the Y location of the ModelObject, depending on the implementation
     * @return integer representing Y coordinate
     */
    public int getY();
}
