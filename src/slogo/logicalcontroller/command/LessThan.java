package slogo.logicalcontroller.command;

public class LessThan implements Command {
    private double value;

    public LessThan(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "LessThan";
    }
}
