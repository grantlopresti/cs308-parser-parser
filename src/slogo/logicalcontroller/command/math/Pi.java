package slogo.logicalcontroller.command.math;

import slogo.logicalcontroller.command.Command;

public class Pi extends MathCommand {

    public Pi(){
        super();
    }

    @Override
    public void performMath() {
        setReturnValue(Math.PI);
    }

    @Override
    public String getCommandType() {
        return "Pi";
    }
}
