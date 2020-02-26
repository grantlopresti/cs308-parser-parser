package slogo.logicalcontroller.command.controlflow;

import slogo.logicalcontroller.command.Command;

public class For extends ControlFlowCommand {
    private double value;

    public For(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "For";
    }
}
