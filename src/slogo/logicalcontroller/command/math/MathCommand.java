package slogo.logicalcontroller.command.math;

import slogo.logicalcontroller.command.Command;

/**
 * Abstract class for MathCommand
 * @author Math Smith, Alex Xu
 */
public abstract class MathCommand implements Command {

    private double returnValue;
    protected double myArgument1;
    protected double myArgument2;

    public MathCommand() {
    }

    public MathCommand(String input1) {
        this.myArgument1 = Double.parseDouble(input1);
    }

    public MathCommand(String input1, String input2) {
        this.myArgument1 = Double.parseDouble(input1);
        this.myArgument2 = Double.parseDouble(input2);
    }

    public abstract void performMath();

    protected void setReturnValue(double value){
       returnValue = value;
    }

    @Override
    public double getValue() {
        return returnValue;
    }

}
