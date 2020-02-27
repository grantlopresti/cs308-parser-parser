package slogo.logicalcontroller.command.modifier;

import slogo.logicalcontroller.command.Command;

public class Left extends ModifierCommand {
    private double value;

    public Left(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "Left";
    }
}