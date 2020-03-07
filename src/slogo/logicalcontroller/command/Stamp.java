package slogo.logicalcontroller.command;

import slogo.model.ModelTurtle;

public class Stamp implements Command {
    private double value;

    public Stamp(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "Stamp";
    }

    @Override
    public String execute(ModelTurtle turtle) {
        return null;
    }
}
