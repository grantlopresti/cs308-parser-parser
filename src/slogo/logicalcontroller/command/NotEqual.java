package slogo.logicalcontroller.command;

public class NotEqual implements Command {
    private double value;

    public NotEqual(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "NotEqual";
    }
}
