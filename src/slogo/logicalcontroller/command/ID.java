package slogo.logicalcontroller.command;

public class ID implements Command {
    private double value;

    public ID(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "ID";
    }
}
