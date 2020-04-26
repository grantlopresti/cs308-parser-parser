package slogo.logicalcontroller.command.math;

import slogo.logicalcontroller.command.Command;
import slogo.model.ModelTurtle;

import java.util.List;

public class Sine extends MathCommand {

    public Sine(List<String> input){
        super(input.get(0));
    }

    @Override
    public String execute(ModelTurtle turtle) {
        return Double.toString(Math.sin(this.myArgument1));
    }

    @Override
    public String getCommandType() {
        return "Sine";
    }
}
