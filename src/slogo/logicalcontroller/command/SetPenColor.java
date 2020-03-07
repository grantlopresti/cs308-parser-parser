package slogo.logicalcontroller.command;

import slogo.logicalcontroller.variable.VariableList;
import slogo.model.ModelTurtle;

public class SetPenColor implements Command {
    private double value;

    public SetPenColor(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "SetPenColor";
    }

    @Override
    public String execute(ModelTurtle turtle) {
        return null;
    }

    @Override
    public String execute(VariableList list) {
        return null;
    }
}
