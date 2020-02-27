package slogo.logicalcontroller.command.math;

import slogo.logicalcontroller.command.Command;

public class ArcTangent extends MathCommand {
    private double value;

    public ArcTangent(String inputvalue){
        super();
        value = Double.parseDouble(inputvalue);
    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "ArcTangent";
    }

}
