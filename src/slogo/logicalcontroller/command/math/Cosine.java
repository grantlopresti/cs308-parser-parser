package slogo.logicalcontroller.command.math;

import slogo.logicalcontroller.command.Command;

import java.util.List;

public class Cosine extends MathCommand {

    public Cosine(List<String> input){
        super(input.get(0));
    }

    @Override
    public String execute() {
        return Double.toString(Math.cos(this.myArgument1));
    }

    @Override
    public String getCommandType() {
        return "Cosine";
    }
}
