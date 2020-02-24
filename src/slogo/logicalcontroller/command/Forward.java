package slogo.logicalcontroller.command;

public class Forward implements Command {

    private double value;

    public Forward(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "Forward";
    }
}
