package slogo.logicalcontroller.command;

public class Or implements Command {
    private double value;

    public Or(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "Or";
    }
}
