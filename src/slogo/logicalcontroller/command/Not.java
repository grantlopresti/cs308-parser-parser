package slogo.logicalcontroller.command;

public class Not implements Command {
    private double value;

    public Not(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "Not";
    }
}
