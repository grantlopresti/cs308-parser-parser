package slogo.logicalcontroller.command.comparison;

import slogo.logicalcontroller.command.Command;

public class Equal extends ComparisonCommand {
    private double value;

    public Equal(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "Equal";
    }
}
