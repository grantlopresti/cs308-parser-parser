package slogo.logicalcontroller.command.math;

import slogo.logicalcontroller.command.Command;

public class Difference extends MathCommand {
    private double value;

    public Difference(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "Difference";
    }
}
