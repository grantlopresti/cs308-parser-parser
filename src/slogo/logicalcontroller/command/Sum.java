package slogo.logicalcontroller.command;

public class Sum implements Command {
    private double value;

    public Sum(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "Sum";
    }
}
