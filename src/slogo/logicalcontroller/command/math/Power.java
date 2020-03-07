package slogo.logicalcontroller.command.math;

import slogo.logicalcontroller.command.Command;
import slogo.model.ModelTurtle;

import java.util.List;

public class Power extends MathCommand {

    public Power(List<String> input){
        super(input.get(0), input.get(1));
    }

    @Override
    public String execute(ModelTurtle turtle) {
        return Double.toString(Math.pow(this.myArgument1, this.myArgument2));
    }

    @Override
    public String getCommandType() {
        return "Power";
    }
}
