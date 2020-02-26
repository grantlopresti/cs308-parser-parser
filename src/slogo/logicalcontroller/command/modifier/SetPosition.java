package slogo.logicalcontroller.command.modifier;

import slogo.logicalcontroller.command.Command;

public class SetPosition extends ModifierCommand {
    private double value;

    public SetPosition(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "SetPosition";
    }
}
