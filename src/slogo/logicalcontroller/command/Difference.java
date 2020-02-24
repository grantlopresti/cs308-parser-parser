package slogo.logicalcontroller.command;

public class Difference implements Command {
    private double value;

    public Difference(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "Difference";
    }
}
