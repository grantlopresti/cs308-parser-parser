package slogo.logicalcontroller.command;

public class For implements Command {
    private double value;

    public For(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "For";
    }
}
