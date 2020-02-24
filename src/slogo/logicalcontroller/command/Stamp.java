package slogo.logicalcontroller.command;

public class Stamp implements Command {
    private double value;

    public Stamp(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "Stamp";
    }
}
