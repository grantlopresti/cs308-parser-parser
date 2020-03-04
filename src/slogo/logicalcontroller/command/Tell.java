package slogo.logicalcontroller.command;

public class Tell implements Command {
    private double value;

    public Tell(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "Tell";
    }
}
