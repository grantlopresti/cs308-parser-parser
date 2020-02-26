package slogo.visualcontroller;

import javafx.beans.property.Property;
import slogo.exceptions.LogicalException;
import slogo.logicalcontroller.command.Command;
import slogo.logicalcontroller.variable.Variable;
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
     */
    public void moveModelObject(ModelCollection modelCollection);

    public void updateCommands(String command);

    public void updateErrors(LogicalException e);

    public void updateVariables(Variable v);

    public Property getProperty(VisualProperty type);

}
