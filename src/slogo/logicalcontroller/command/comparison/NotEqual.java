package slogo.logicalcontroller.command.comparison;

import slogo.logicalcontroller.command.Command;

public class NotEqual extends ComparisonCommand {
    private double value;

    public NotEqual(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "NotEqual";
    }
}
