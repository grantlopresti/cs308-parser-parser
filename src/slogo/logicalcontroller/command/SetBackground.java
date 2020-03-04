package slogo.logicalcontroller.command;

public class SetBackground implements Command {
    private double value;

    public SetBackground(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "SetBackground";
    }
}
