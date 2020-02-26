package slogo.logicalcontroller.command;

public class ID implements Command {
    private double myValue;

    public ID(String inputvalue){
        myValue = Double.parseDouble(inputvalue);

    }

    @Override
    public double getValue() {
        return myValue;
    }

    @Override
    public String getCommandType() {
        return "ID";
    }
}
