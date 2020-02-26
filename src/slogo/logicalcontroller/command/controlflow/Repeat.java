package slogo.logicalcontroller.command.controlflow;

import slogo.logicalcontroller.command.Command;

public class Repeat extends ControlFlowCommand {
    private double value;

    public Repeat(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "Repeat";
    }
}
