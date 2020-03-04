package slogo.logicalcontroller.command.controlflow;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements the DOTIMES Factory Design Pattern
 * @author Alex Xu
 */
public class DoTimes extends ControlFlowCommand {
    private int repeatCount;

    /**
     * Constructor for the DOTIMES command. Takes in a number of repeats (rounded to nearest integer)
     * @param repeat
     * @param rawInput
     */
    public DoTimes(double repeat, List<String> rawInput){
        super(rawInput);
        repeatCount = (int)Math.round(repeat);
    }

    @Override
    protected void unravelCode() {
        List<String> myBody = this.getBody();
        List<String> result = new ArrayList<>();

        for(int i = 0; i<repeatCount; i++){
            result.add("SET :variable " + (i+1));
            result.addAll(myBody);
        }
        setUnraveledCode(result);
    }


}