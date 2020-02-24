package slogo.logicalcontroller.command;

public class Random implements Command {
    private double value;

    public Random(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "Random";
    }
}
