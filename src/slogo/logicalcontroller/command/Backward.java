package slogo.logicalcontroller.command;

public class Backward implements Command {
    private double value;

    public Backward(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "Backward";
    }
}
