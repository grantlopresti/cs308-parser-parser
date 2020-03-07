package slogo.visualcontroller;

import javafx.beans.property.Property;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import slogo.exceptions.DeprecationException;
import slogo.exceptions.LogicalException;
import slogo.logicalcontroller.command.Command;
import slogo.logicalcontroller.variable.Variable;
import slogo.logicalcontroller.variable.VariableList;
import slogo.model.ModelCollection;
import slogo.model.ModelTurtle;
import slogo.view.windows.SlogoView;

import java.util.List;

public interface VisualInterface {

    /**
     * Public method callable by the logical controller to adjust the animation rate
     * @param rate is the new animation rate for adding lines into the view
     */
    void setAnimationRate(double rate);

    /**
     * Called by logical controller to update the view
     * @param model all turtles currently on the screen
     * @param variableList list of variables to populate within the model
     * @param latestCommand last command run
     */
    void update(ModelCollection model, VariableList variableList, Command latestCommand);

    /**
     *
     * @param e logical exception thrown during logical controller parsing
     */
    void updateErrors(LogicalException e);

    void changeTurtleImage(String newValue);

    Property getProperty(VisualProperty type);

    void setSlogoView(SlogoView view);

    void updateCommand(String fullUserInput);

    void deprecateProgram(DeprecationException e);
}
