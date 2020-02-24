package slogo.logicalcontroller.command;

public class IsPenDown implements Command {
    private double value;

    public IsPenDown(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "IsPenDown";
    }
}
