package slogo.logicalcontroller.command.controlflow;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements the FOR Factory Design Pattern
 * @author Alex Xu
 */
public class For extends ControlFlowCommand {
    private int myStartValue;
    private int myEndValue;

    /**
     * Constructor for the For Command Object. Takes in a starting index and an ending index. Rounds to the nearest integer.
     * @param start
     * @param end
     * @param rawInput
     */
    public For(double start, double end, List<String> rawInput){
        super(rawInput);
        myStartValue = (int)Math.round(start);
        myEndValue = (int)Math.round(end);
    }

    @Override
    protected void unravelCode() {
        List<String> myBody = this.getBody();
        List<String> result = new ArrayList<>();

        int repeatCount = myEndValue - myStartValue;

        for(int i = 0; i<repeatCount; i++){
            result.add("SET :variable " + (myStartValue+i));
            result.addAll(myBody);
        }
        setUnraveledCode(result);
    }
}
