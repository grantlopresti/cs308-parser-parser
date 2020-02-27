package slogo.logicalcontroller.command.math;

import slogo.logicalcontroller.command.Command;

public class Minus extends MathCommand {

    public Minus(String input) {
        super(input);
    }

    @Override
    public double performMath() {
        return this.myArgument1 * -1;
    }

    @Override
    public String getCommandType() {
        return "Minus";
    }
}
