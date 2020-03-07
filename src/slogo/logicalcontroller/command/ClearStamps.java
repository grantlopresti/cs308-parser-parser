package slogo.logicalcontroller.command;

import slogo.model.ModelTurtle;

public class ClearStamps implements Command {
    private double value;

    public ClearStamps(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "ClearStamps";
    }

    @Override
    public String execute(ModelTurtle turtle) {
        return null;
    }
}
