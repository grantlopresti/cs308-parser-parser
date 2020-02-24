package slogo.logicalcontroller.command;

public class Repeat implements Command {
    private double value;

    public Repeat(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "Repeat";
    }
}
