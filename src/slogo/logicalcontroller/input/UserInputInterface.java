package slogo.logicalcontroller.input;

import slogo.logicalcontroller.command.Command;

import java.util.List;

public interface UserInputInterface {

    /**
     *
     * @return
     */
    public Command getNextCommand();

    /**
     *
     * @param code
     */
    public void setCodeReplacement(List<String> code, Command command);

    /**
     * Signals to Logical Controller (through Parser), that all code has been executed
     * @return that no more commands exist in the user input to parse
     */
    public boolean isFinished();

}
