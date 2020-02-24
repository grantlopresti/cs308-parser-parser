package slogo.logicalcontroller.command;

public class Remainder implements Command {
    private double value;

    public Remainder(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "Remainder";
    }
}
