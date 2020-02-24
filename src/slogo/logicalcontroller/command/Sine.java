package slogo.logicalcontroller.command;

public class Sine implements Command {
    private double value;

    public Sine(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "Sine";
    }
}
