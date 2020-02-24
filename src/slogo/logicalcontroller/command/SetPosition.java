package slogo.logicalcontroller.command;

public class SetPosition implements Command {
    private double value;

    public SetPosition(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "SetPosition";
    }
}
