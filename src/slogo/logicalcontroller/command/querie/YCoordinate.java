package slogo.logicalcontroller.command.querie;

import slogo.logicalcontroller.command.Command;

public class YCoordinate extends QuerieCommand {
    private double value;

    public YCoordinate(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "YCoordinate";
    }
}
