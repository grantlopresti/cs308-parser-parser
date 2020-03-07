package slogo.logicalcontroller.command.variables;

import slogo.logicalcontroller.command.Command;
import slogo.model.ModelTurtle;

public abstract class VariableCommand implements Command {

    @Override
    public String getCommandType() {
        String className = this.getClass().getSimpleName();
        return className;
    }

    @Override
    public String execute(ModelTurtle turtle){
        return null;
    }
}
