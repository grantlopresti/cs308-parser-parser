package slogo.logicalcontroller.command.unimplemented;

import slogo.logicalcontroller.command.Command;
import slogo.logicalcontroller.variable.VariableList;
import slogo.model.ModelTurtle;

public abstract class UnimplementedCommand implements Command {

    public UnimplementedCommand() {
        ;
    }

    @Override
    public String execute(ModelTurtle turtle) {
        return null;
    }

    @Override
    public String execute(VariableList turtle) {
        return null;
    }


}
