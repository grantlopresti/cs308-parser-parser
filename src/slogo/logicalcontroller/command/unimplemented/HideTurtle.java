package slogo.logicalcontroller.command.unimplemented;

import slogo.logicalcontroller.command.Command;
import slogo.logicalcontroller.variable.VariableList;
import slogo.model.ModelTurtle;

import java.util.List;

public class HideTurtle extends UnimplementedCommand {

    public HideTurtle(List<String> args){
        super();
    }

    @Override
    public String getCommandType() {
        return "HideTurtle";
    }
}
