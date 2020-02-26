package slogo.logicalcontroller.command.modifier;

import slogo.logicalcontroller.command.Command;

public class PenDown extends ModifierCommand {
    private double value;

    public PenDown(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "PenDown";
    }
}
