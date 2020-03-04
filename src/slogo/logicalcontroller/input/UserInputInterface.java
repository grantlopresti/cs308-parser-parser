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
    public void setCodeReplacement(List<String> code);

    /**
     *
     * @return true when there are more lines to get commands from
     */
    public boolean hasNext();

}
