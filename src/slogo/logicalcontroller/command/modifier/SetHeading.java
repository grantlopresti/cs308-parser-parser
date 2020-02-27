package slogo.logicalcontroller.command.modifier;

import slogo.logicalcontroller.command.Command;

public class SetHeading extends ModifierCommand {
    private double value;

    public SetHeading(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "SetHeading";
    }

    @Override
    public String toString(){
        return (getCommandType() + " " + getValue());
    }
}
