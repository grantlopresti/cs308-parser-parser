package slogo.logicalcontroller.command;

public class Equal implements Command {
    private double value;

    public Equal(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "Equal";
    }
}
