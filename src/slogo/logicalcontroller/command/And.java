package slogo.logicalcontroller.command;

public class And implements Command {
    private double value;

    public And(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "And";
    }
}
