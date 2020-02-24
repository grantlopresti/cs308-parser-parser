package slogo.logicalcontroller.command;

public class Tangent implements Command {
    private double value;

    public Tangent(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "Tangent";
    }
}
