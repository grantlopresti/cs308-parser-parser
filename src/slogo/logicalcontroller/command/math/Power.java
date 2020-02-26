package slogo.logicalcontroller.command.math;

import slogo.logicalcontroller.command.Command;

public class Power extends MathCommand {
    private double value;

    public Power(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "Power";
    }
}
