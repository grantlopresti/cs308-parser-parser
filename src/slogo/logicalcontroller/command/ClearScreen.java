package slogo.logicalcontroller.command;

public class ClearScreen implements Command {
    private double value;

    public ClearScreen(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "ClearScreen";
    }
}
