package slogo.logicalcontroller.command;

public class GreaterThan implements Command {
    private double value;

    public GreaterThan(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "GreaterThan";
    }
}
