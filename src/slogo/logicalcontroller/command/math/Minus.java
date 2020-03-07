package slogo.logicalcontroller.command.math;

import slogo.model.ModelTurtle;

import java.util.List;

//TODO: Difference between Difference and Minus commands?
public class Minus extends MathCommand {

    public Minus(List<String> input){
        super(input.get(0));
    }

    @Override
    public String execute(ModelTurtle turtle) {
        return Double.toString(-1 * this.myArgument1);
    }

    @Override
    public String getCommandType() {
        return "Minus";
    }
}
