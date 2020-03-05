package slogo.logicalcontroller.command.math;

import slogo.logicalcontroller.command.Command;

import java.util.List;

public class NaturalLog extends MathCommand {

    public NaturalLog(List<String> input){
        super(input.get(0));
    }

    @Override
    public String execute() {
        return Double.toString(Math.log(this.myArgument1));
    }

    @Override
    public String getCommandType() {
        return "NaturalLog";
    }
}
