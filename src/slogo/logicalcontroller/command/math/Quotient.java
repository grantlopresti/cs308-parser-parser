package slogo.logicalcontroller.command.math;

import slogo.logicalcontroller.command.Command;

public class Quotient extends MathCommand {

    public Quotient(String in1, String in2){
        super(in1, in2);
    }

    @Override
    public void performMath() {
        setReturnValue(this.myArgument1 / this.myArgument2);
    }

    @Override
    public String getCommandType() {
        return "Quotient";
    }
}
