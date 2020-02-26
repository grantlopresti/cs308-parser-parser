package slogo.logicalcontroller.command.modifier;

import slogo.logicalcontroller.command.Command;

public class ShowTurtle extends ModifierCommand {
    private double value;

    public ShowTurtle(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "ShowTurtle";
    }
}
