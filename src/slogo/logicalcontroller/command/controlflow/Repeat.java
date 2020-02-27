package slogo.logicalcontroller.command.controlflow;

import slogo.logicalcontroller.command.Command;

import java.util.ArrayList;
import java.util.List;

public class Repeat extends ControlFlowCommand {
    private double value;
    private List<Command> repCommands;

    public Repeat(String inputvalue, List<Command> repeatCommands){
        this.value = Double.parseDouble(inputvalue);
        this.repCommands = repeatCommands;
    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "Repeat";
    }

    public List<Command> getRepCommands(){
        return this.repCommands;
    }
}
