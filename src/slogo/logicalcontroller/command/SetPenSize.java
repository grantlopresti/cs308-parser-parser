package slogo.logicalcontroller.command;

public class SetPenSize implements Command {
    private double value;

    public SetPenSize(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "SetPenSize";
    }
}
