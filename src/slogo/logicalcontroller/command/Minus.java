package slogo.logicalcontroller.command;

public class Minus implements Command {
    private double value;

    public Minus(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "Minus";
    }
}
