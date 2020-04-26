package slogo.logicalcontroller.command.math;

import slogo.logicalcontroller.command.Command;
import slogo.model.ModelTurtle;

import java.util.List;

public class ArcTangent extends MathCommand {

    public ArcTangent(List<String> input){
        super(input.get(0));
    }

    @Override
    public String execute(ModelTurtle turtle) {
        return Double.toString(Math.atan(this.myArgument1));
    }

    @Override
    public String getCommandType() {
        return "ArcTangent";
    }

}
