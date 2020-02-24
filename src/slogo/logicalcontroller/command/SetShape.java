package slogo.logicalcontroller.command;

public class SetShape implements Command {
    private double value;

    public SetShape(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "SetShape";
    }
}
