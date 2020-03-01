package slogo.logicalcontroller.command.math;

//TODO: Difference between Difference and Minus commands?
public class Minus extends MathCommand {

    public Minus(String input) {
        super(input);
    }

    @Override
    public void performMath() {
        setReturnValue(this.myArgument1 * -1);
    }

    @Override
    public String getCommandType() {
        return "Minus";
    }
}
