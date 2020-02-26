package slogo.logicalcontroller.command.comparison;

import slogo.logicalcontroller.command.Command;

public class And extends ComparisonCommand {
    private double value;

    public And(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "And";
    }
}
