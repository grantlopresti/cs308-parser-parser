package slogo.logicalcontroller.command;

import slogo.model.ModelTurtle;

public class ClearScreen implements Command {
    private double value;

    public ClearScreen(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "ClearScreen";
    }

    @Override
    public String execute(ModelTurtle turtle) {
        return null;
    }
}
