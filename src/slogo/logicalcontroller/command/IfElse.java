package slogo.logicalcontroller.command;

public class IfElse implements Command {
    private double value;

    public IfElse(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "IfElse";
    }
}
