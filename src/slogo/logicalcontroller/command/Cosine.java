package slogo.logicalcontroller.command;

public class Cosine implements Command {
    private double value;

    public Cosine(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "Cosine";
    }
}
