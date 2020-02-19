package slogo.model;

/**
 * Class handles encapsulation of Model Turtle information and movement logic.
 * @author Alex Xu
 */
public class ModelTurtle extends ModelObject {

    private Pen myPen;

    /**
     * Default constructor of ModelTurtle object
     */
    public ModelTurtle(){
        super();
        myPen = new Pen();
    }

    /**
     * Overrides method defined in the ModelObject Interface.
     * Sets the Pen thickness to the specified value
     */
    @Override
    public void setPenThickness(double thickness){
        myPen.setThickness(thickness);
    }

    /**
     * Overrides method defined in the ModelObject Interface. Returns the pen state
     * @return boolean representing whether or not the pen is active
     */
    @Override
    public boolean isPenActive() {
        return myPen.isActive();
    }

    /**
     * Overrides method defined in the ModelObject Interface. Returns the pen thickness.
     * @return double representing pen thickness.
     */
    @Override
    public double getPenThickness() {
        return myPen.getThickness();
    }
}
