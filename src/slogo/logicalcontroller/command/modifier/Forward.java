package slogo.logicalcontroller.command.modifier;

public class Forward extends ModifierCommand {

    private double value;

    public Forward(String inputvalue){
        value = Double.parseDouble(inputvalue);

    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "Forward";
    }

    @Override
    public String toString(){
        return (getCommandType() + " " + getValue());
    }
}
