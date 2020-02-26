package slogo.visualcontroller;

import slogo.logicalcontroller.command.Command;
import slogo.model.ModelCollection;
import slogo.model.ModelTurtle;

import java.util.List;

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

    public void updateCommands(List<Command> commands);

    public void updateErrors(List<RuntimeException> exceptions);

}
