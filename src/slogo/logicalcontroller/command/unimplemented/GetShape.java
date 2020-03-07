package slogo.logicalcontroller.command.unimplemented;

import slogo.logicalcontroller.command.Command;
import slogo.logicalcontroller.variable.VariableList;
import slogo.model.ModelTurtle;

import java.util.List;

public class GetShape extends UnimplementedCommand {

    public GetShape(List<String> args){
        super();
    }

    @Override
    public String getCommandType() {
        return "GetShape";
    }

    @Override
    public String execute(VariableList list) {
        return null;
    }
}
