package slogo.logicalcontroller.command.math;

import slogo.logicalcontroller.command.Command;

public class Tangent extends MathCommand{

    public Tangent(String input){
        super(input);
    }

    @Override
    public void performMath() {
        setReturnValue(Math.tan(this.myArgument1));
    }

    @Override
    public String getCommandType() {
        return "Tangent";
    }
}
