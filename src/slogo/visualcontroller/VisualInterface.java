package slogo.visualcontroller;

import slogo.logicalcontroller.command.Command;
import slogo.model.ModelCollection;
import slogo.model.ModelTurtle;

public interface VisualInterface {

    /**
     * Public method callable by the logical controller to adjust the animation rate
     * @param rate is the new animation rate for adding lines into the view
     */
    public void setAnimationRate(double rate);

    /**
     * Called by the logical controller to update turtle state and draw shapes in Slogo view
     *
     * @param modelCollection collection of model objects
     * @param command to determine how the turtle changes
     */
    public void moveModelObject(ModelCollection modelCollection, Command command);

}
