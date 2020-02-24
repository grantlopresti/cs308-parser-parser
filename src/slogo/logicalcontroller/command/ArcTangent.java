package slogo.logicalcontroller.command;

public class ArcTangent implements Command {
    private double value;

    public ArcTangent(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "ArcTangent";
    }
}
