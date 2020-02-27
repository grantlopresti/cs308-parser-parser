package slogo.logicalcontroller.command.math;

import slogo.logicalcontroller.command.Command;

public class ArcTangent extends MathCommand {

    public ArcTangent(String input){
        super(input);
    }

    @Override
    public double performMath() {
        return Math.atan(myArgument1);
    }

    @Override
    public String getCommandType() {
        return "ArcTangent";
    }
}
