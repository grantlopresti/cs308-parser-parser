package slogo.logicalcontroller.command.math;

import slogo.logicalcontroller.command.Command;

public class Random extends MathCommand {

    public Random(String input){
        super(input);
    }

    @Override
    public double performMath() {
        return Math.random() * this.myArgument1;
    }

    @Override
    public String getCommandType() {
        return "Random";
    }
}
