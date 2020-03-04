package slogo.logicalcontroller.command;

public class SetPenColor implements Command {
    private double value;

    public SetPenColor(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "SetPenColor";
    }
}
