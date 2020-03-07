package slogo.logicalcontroller.command;

import slogo.model.ModelTurtle;

public class MakeVariable implements Command {
    private double value;

    public MakeVariable(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "MakeVariable";
    }

    @Override
    public String execute(ModelTurtle turtle) {
        return null;
    }
}
