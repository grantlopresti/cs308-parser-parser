package slogo.logicalcontroller.command;

public class SetTowards implements Command {
    private double value;

    public SetTowards(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "SetTowards";
    }
}
