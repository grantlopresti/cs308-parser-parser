package slogo.logicalcontroller.input;

import slogo.logicalcontroller.command.Command;

import java.util.List;

public interface UserInputInterface {

    public Command getNextCommand();

    public void setCodeReplacement(List<String> code);

}
