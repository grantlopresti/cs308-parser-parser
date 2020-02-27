package slogo.logicalcontroller.command.modifier;

import slogo.logicalcontroller.command.Command;

public class Backward extends ModifierCommand {
    private double value;

    public Backward(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "Backward";
    }

    @Override
    public String toString(){
        return (getCommandType() + " " + getValue());
    }
}
