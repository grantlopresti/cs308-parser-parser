package slogo.logicalcontroller.command.modifier;

import slogo.model.ModelTurtle;

import java.lang.reflect.InvocationTargetException;

public interface ModifierInterface {

    /**
     * Executes the command
     * @returns the string value to replace within the list
     */
    public abstract String execute(ModelTurtle turtle);
}
