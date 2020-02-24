package slogo.logicalcontroller.command;

public class Power implements Command {
    private double value;

    public Power(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "Power";
    }
}
