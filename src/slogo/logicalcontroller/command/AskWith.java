package slogo.logicalcontroller.command;

public class AskWith implements Command {
    private double value;

    public AskWith(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "AskWidth";
    }
}
