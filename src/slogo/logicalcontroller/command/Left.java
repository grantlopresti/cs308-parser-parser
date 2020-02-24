package slogo.logicalcontroller.command;

public class Left implements Command {
    private double value;

    public Left(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "Left";
    }
}
