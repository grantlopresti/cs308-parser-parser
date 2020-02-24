package slogo.logicalcontroller.command;

public class DoTimes implements Command {
    private double value;

    public DoTimes(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "DoTimes";
    }
}
