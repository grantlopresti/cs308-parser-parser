package slogo.logicalcontroller.command;

import slogo.model.ModelTurtle;

public class SetPalette implements Command {
    private double value;

    public SetPalette(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "SetPalette";
    }

    @Override
    public String execute(ModelTurtle turtle) {
        return null;
    }
}
