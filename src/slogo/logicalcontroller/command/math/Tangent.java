package slogo.logicalcontroller.command.math;

import slogo.logicalcontroller.command.Command;
import slogo.model.ModelTurtle;

import java.util.List;

public class Tangent extends MathCommand{

    public Tangent(List<String> input){
        super(input.get(0));
    }

    @Override
    public String execute(ModelTurtle turtle) {
        return Double.toString(Math.tan(this.myArgument1));
    }

    @Override
    public String getCommandType() {
        return "Tangent";
    }
}
