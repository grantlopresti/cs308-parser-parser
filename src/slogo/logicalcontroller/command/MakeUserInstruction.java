package slogo.logicalcontroller.command;

public class MakeUserInstruction implements Command {
    private double value;

    public MakeUserInstruction(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "MakeUserInstruction";
    }
}
