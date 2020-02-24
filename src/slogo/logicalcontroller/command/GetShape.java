package slogo.logicalcontroller.command;

public class GetShape implements Command {
    private double value;

    public GetShape(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "GetShape";
    }
}
