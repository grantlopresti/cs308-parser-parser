package slogo.logicalcontroller.command;

public class XCoordinate implements Command {
    private double value;

    public XCoordinate(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "XCoordinate";
    }
}
