package slogo.logicalcontroller.command;

public class ShowTurtle implements Command {
    private double value;

    public ShowTurtle(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "ShowTurtle";
    }
}
