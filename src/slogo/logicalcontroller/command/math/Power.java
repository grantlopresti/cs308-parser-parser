package slogo.logicalcontroller.command.math;

import slogo.logicalcontroller.command.Command;

public class Power extends MathCommand {

    public Power(String base, String exp){
        super(base, exp);
    }

    @Override
    public void performMath() {
        setReturnValue(Math.pow(this.myArgument1, this.myArgument2));
    }

    @Override
    public String getCommandType() {
        return "Power";
    }
}
