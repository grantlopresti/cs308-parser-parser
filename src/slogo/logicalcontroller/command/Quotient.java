package slogo.logicalcontroller.command;

public class Quotient implements Command {
    private double value;

    public Quotient(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "Quotient";
    }
}
