package slogo.logicalcontroller.command.controlflow;

import slogo.logicalcontroller.command.Command;

import java.util.ArrayList;

public class Repeat extends ControlFlowCommand {
    private double value;
    private ArrayList<Command> repCommands;

    public Repeat(String inputvalue, ArrayList<Command> repeatCommands){
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

    public ArrayList<Command> getRepCommands(){
        return this.repCommands;
    }
}
