package slogo.logicalcontroller.command;

public class If implements Command {
    private double value;

    public If(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "If";
    }
}
