package slogo.logicalcontroller.command.modifier;

import slogo.logicalcontroller.command.Command;

public class PenUp extends ModifierCommand {
    private double value;

    public PenUp(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "PenUp";
    }
}
