package slogo.logicalcontroller.command.math;

import slogo.logicalcontroller.command.Command;

import java.util.List;

public class Random extends MathCommand {

    public Random(List<String> input){
        super(input.get(0));
    }

    @Override
    public String execute() {
        return Double.toString(Math.random() * this.myArgument1);
    }

    @Override
    public String getCommandType() {
        return "Random";
    }
}
