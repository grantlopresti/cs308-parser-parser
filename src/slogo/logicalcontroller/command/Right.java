package slogo.logicalcontroller.command;

public class Right implements Command {
    private double value;

    public Right(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "Right";
    }

    @Override
    public String toString(){
        return (getCommandType() + " " + getValue());
    }
}
