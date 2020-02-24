package slogo.logicalcontroller.command;

public class YCoordinate implements Command {
    private double value;

    public YCoordinate(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "YCoordinate";
    }
}
