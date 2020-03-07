package slogo.logicalcontroller.command;

import slogo.model.ModelTurtle;

public class GetShape implements Command {
    private double value;

    public GetShape(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "GetShape";
    }

    @Override
    public String execute(ModelTurtle turtle) {
        return null;
    }
}
