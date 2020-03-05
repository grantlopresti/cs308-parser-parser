package slogo.logicalcontroller.command;

public class HideTurtle implements Command {
    private double value;

    public HideTurtle(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "HideTurtle";
    }
}
