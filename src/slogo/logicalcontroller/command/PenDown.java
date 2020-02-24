package slogo.logicalcontroller.command;

public class PenDown implements Command {
    private double value;

    public PenDown(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "PenDown";
    }
}
