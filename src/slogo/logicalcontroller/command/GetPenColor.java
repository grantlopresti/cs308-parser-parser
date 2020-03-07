package slogo.logicalcontroller.command;

import slogo.model.ModelTurtle;

public class GetPenColor implements Command{
    private double value;

    public GetPenColor(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "GetPenColor";
    }

    @Override
    public String execute(ModelTurtle turtle) {
        return null;
    }
}
