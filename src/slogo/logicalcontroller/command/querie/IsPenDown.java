package slogo.logicalcontroller.command.querie;

import slogo.logicalcontroller.command.Command;

public class IsPenDown extends QuerieCommand {
    private double value;

    public IsPenDown(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "IsPenDown";
    }

    @Override
    public String toString(){
        return (getCommandType() + " " + getValue());
    }
}
