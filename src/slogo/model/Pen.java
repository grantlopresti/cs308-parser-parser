package slogo.model;

/**
 * Class that encapsulates the Pen.
 * @author Alex Xu
 */
public class Pen {
    public static final double PEN_DEFAULT_THICKNESS = 1.0;
    public static final boolean PEN_DEFAULT_STATE = true;

    boolean active;
    double myThickness;

    /**
     * Constructor for a Pen object.
     */
    public Pen(){
        active = PEN_DEFAULT_STATE;
        myThickness = PEN_DEFAULT_THICKNESS;
    }

    /**
     * Sets the thickness with the specified value.
     * @param value
     */
    protected void setThickness(double value){
        if(value >= 0) {
            myThickness = value;
        }
        else{
            myThickness = 0;
        }
    }

    /**
     * Whether or not the Pen is active
     * @return boolean representing pen state
     */
    protected boolean isActive(){
        return active;
    }

    /**
     * Return the Thickness of the Pen.
     * @return double representing the thickness of the Pen.
     */
    protected double getThickness(){
        return myThickness;
    }



    protected void penDown(){
        active = true;
    }

    protected void penUp(){
        active = false;
    }
}
