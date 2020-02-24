package slogo.logicalcontroller.command;

public class Pi implements Command{
    private double value;

    public Pi(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "Pi";
    }
}
