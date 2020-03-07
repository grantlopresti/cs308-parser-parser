package slogo.logicalcontroller.command;

import slogo.model.ModelTurtle;

//TODO: Refactor into two different classes because there are commands that interact with the model, and there are commands that don't. - Alex
public interface Command {

    /**
     * Getter method to return the type of the Command object. Ex: fd, rt
     * @return
     */
    public String getCommandType();

    /**
     * Method to return the String representation of the Command object.
     * @return
     */
    public String toString();

    String execute(ModelTurtle turtle);
}
