package slogo.logicalcontroller.command.math;

import slogo.logicalcontroller.command.Command;

public class Pi extends MathCommand {
    private double value;

    public Pi(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "Pi";
    }
}
