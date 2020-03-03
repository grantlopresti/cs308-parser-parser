package slogo.logicalcontroller.command.controlflow;

import slogo.logicalcontroller.command.Command;

import java.util.ArrayList;
import java.util.List;

public class Repeat extends ControlFlowCommand {
    private double value;
    private List<Command> repCommands;
    private List<Command> allRepCommands;

    public Repeat(String inputvalue, List<Command> repeatCommands){
        super(inputvalue);
        this.value = Double.parseDouble(inputvalue);
        this.repCommands = repeatCommands;
    }

    @Override
    public void unravelCode() {

    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "Repeat";
    }

    public List<Command> getRepCommand(){
        return this.repCommands;
    }

    public List<Command> getAllRepCommands(){
        this.allRepCommands = new ArrayList<Command>();
        for(int i = 0; i<(int)value; i++){
            this.allRepCommands.addAll(getRepCommand());
        }

        return this.allRepCommands;
    }
}
