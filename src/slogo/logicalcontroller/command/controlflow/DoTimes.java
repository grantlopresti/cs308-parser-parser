package slogo.logicalcontroller.command.controlflow;

/**
 * Implements the DOTIMES Factory Design Pattern
 * @author Alex Xu
 */
public class DoTimes extends ControlFlowCommand {
    private int repeatCount;

    public DoTimes(int argument, String rawInput){
        super(rawInput);
        repeatCount = argument;
    }

    @Override
    public void unravelCode() {
        String myBody = this.getBody();
        String result = "";

        for(int i = 0; i<repeatCount; i++){
            result += "SET :variable " + (i+1);
            result += myBody;
        }

    }

    @Override
    public double getValue() {
        return repeatCount;
    }

}
