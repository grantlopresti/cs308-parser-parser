package slogo.logicalcontroller.command.modifier;

import slogo.logicalcontroller.command.Command;

public class Right extends ModifierCommand {
    private double value;

    public Right(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "Right";
    }

    @Override
    public String toString(){
        return (getCommandType() + " " + getValue());
    }
}
