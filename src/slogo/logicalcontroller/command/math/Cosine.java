package slogo.logicalcontroller.command.math;

import slogo.logicalcontroller.command.Command;

public class Cosine extends MathCommand {

    public Cosine(String input){
        super(input);
    }

    @Override
    public double performMath() {
        return Math.cos(myArgument1);
    }

    @Override
    public String getCommandType() {
        return "Cosine";
    }
}
