package slogo.model;

/**
 * Class handles encapsulation of Model Turtle information and movement logic.
 * @author Alex Xu
 */
public class ModelTurtle extends ModelObject {

    private Pen myPen;
    private boolean isShowing;
    private boolean isActive;

    /**
     * Default constructor of ModelTurtle object
     */
    public ModelTurtle(){
        super();
        orientTurtle();

    }

    public ModelTurtle(int id) {
        super(id);
        orientTurtle();
    }

    private void orientTurtle() {
        this.myPen = new Pen();
        this.isShowing = true;
        this.isActive = true;
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

    public double penUp(){
        myPen.penUp();
        return 0;
    }
    public double penDown(){
        myPen.penDown();
        return 1;
    }

    public double showTurtle(){
        isShowing = true;
        return 1;
    }
    public double hideTurtle(){
        isShowing = false;
        return 0;
    }

    public void activate() {
        System.out.print("Activating turtle: " + this.ID);
        this.isActive = true;
    }

    public void deactivate() {
        System.out.print("Deactivating turtle: " + this.ID);
        this.isActive = false;
    }

    public boolean isShowing(){
        return isShowing;
    }

    public boolean isActive() {return this.isActive;}

}
