package slogo.logicalcontroller.command;

public class Ask implements Command {
    private double value;

    public Ask(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "Ask";
    }
}
