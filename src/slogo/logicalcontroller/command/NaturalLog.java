package slogo.logicalcontroller.command;

public class NaturalLog implements Command {
    private double value;

    public NaturalLog(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "NaturalLog";
    }
}
