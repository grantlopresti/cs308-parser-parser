package slogo.logicalcontroller.command;

public class MakeVariable implements Command {
    private double value;

    public MakeVariable(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "MakeVariable";
    }
}
