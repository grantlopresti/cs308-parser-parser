package slogo.logicalcontroller.command.controlflow;

import slogo.logicalcontroller.command.Command;

public class IfElse extends ControlFlowCommand {
    private double value;

    public IfElse(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "IfElse";
    }
}
