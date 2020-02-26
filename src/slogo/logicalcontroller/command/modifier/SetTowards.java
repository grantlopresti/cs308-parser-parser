package slogo.logicalcontroller.command.modifier;

import slogo.logicalcontroller.command.Command;

public class SetTowards extends ModifierCommand {
    private double value;

    public SetTowards(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "SetTowards";
    }
}
