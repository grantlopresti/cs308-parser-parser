package slogo.logicalcontroller.command;

public class Turtles implements Command {
    private double value;

    public Turtles(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "Turtles";
    }
}
