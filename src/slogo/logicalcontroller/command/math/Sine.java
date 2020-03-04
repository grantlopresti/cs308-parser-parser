package slogo.logicalcontroller.command.math;

import slogo.logicalcontroller.command.Command;

public class Sine extends MathCommand {

    public Sine(String input){
        super(input);
    }

    @Override
    protected void performMath() {
        setReturnValue(Math.sin(this.myArgument1));
    }

    @Override
    public String getCommandType() {
        return "Sine";
    }
}
