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

    public void update(ModelCollection model, List<Variable> variableList, Command latestCommand);

    public void updateErrors(LogicalException e);


    public Property getProperty(VisualProperty type);

}
