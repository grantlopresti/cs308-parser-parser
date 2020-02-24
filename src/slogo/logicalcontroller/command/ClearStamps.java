package slogo.logicalcontroller.command;

public class ClearStamps implements Command {
    private double value;

    public ClearStamps(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "ClearStamps";
    }
}
