package slogo.logicalcontroller.command.modifier;

import slogo.logicalcontroller.command.Command;

public class PenUp extends ModifierCommand {
    private double myValue;
    private double retValue;

    public PenUp(){

    }

    public double getRetValue(){
        return this.retValue;
    }

    @Override
    public double getValue() {
        return this.myValue;
    }

    @Override
    public String getCommandType() {
        return "PenUp";
    }
}
