package slogo.logicalcontroller.command.math;

import slogo.logicalcontroller.command.Command;
import slogo.model.ModelTurtle;

import java.util.List;

public class Pi extends MathCommand {

    public Pi(List<String> input){
        super();
    }

    @Override
    public String execute(ModelTurtle turtle) {
        return Double.toString(Math.PI);
    }

    @Override
    public String getCommandType() {
        return "Pi";
    }
}
