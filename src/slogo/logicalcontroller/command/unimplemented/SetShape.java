package slogo.logicalcontroller.command.unimplemented;

import slogo.logicalcontroller.command.Command;
import slogo.logicalcontroller.variable.VariableList;
import slogo.model.ModelTurtle;

import java.util.List;

public class SetShape extends UnimplementedCommand {

    public SetShape(List<String> args){
        super();
    }

    @Override
    public String getCommandType() {
        return "SetShape";
    }
}
