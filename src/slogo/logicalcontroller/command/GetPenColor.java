package slogo.logicalcontroller.command;

public class GetPenColor implements Command{
    private double value;

    public GetPenColor(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }
    
    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "GetPenColor";
    }
}
