package slogo.logicalcontroller.command;

public class PenUp implements Command {
    private double value;

    public PenUp(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "PenUp";
    }
}
