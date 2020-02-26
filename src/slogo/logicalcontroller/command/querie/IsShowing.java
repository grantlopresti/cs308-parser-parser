package slogo.logicalcontroller.command.querie;

import slogo.logicalcontroller.command.Command;

public class IsShowing extends QuerieCommand {
    private double value;

    public IsShowing(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "IsShowing";
    }
}
