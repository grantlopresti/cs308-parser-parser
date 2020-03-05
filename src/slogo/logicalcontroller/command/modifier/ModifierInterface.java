package slogo.logicalcontroller.command.modifier;

import slogo.model.ModelTurtle;

import java.lang.reflect.InvocationTargetException;

public interface ModifierInterface {

    /**
     * Executes the command
     * @returns the string value to replace within the list
     */
    public abstract void execute(ModelTurtle turtle);

    /**
     * Called by parser to code replace after modifier has executed
     * @return
     */
    public abstract String codeReplace();



}
