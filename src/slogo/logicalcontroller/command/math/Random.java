package slogo.logicalcontroller.command.math;

import slogo.logicalcontroller.command.Command;

public class Random extends MathCommand {
    private double value;

    public Random(String inputvalue){
        super(inputvalue);
        value = Double.parseDouble(inputvalue);

    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "Random";
    }
}
