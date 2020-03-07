package slogo.logicalcontroller.command.controlflow;

import slogo.model.ModelTurtle;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements the FOR Factory Design Pattern. Assumption is that the input convention is followed from other classes.
 * @author Alex Xu
 */
public class For extends ControlFlowCommand {
    private int myStartValue;
    private int myEndValue;
    private int myIncrement;
    private String variableName;

    /**
     * Constructor for the For Command Object. Takes in a starting index and an ending index. Rounds to the nearest integer.
     */
    public For(List<List<String>> rawInput){
        super(rawInput.get(1));

        variableName = rawInput.get(0).get(0);
        myStartValue = (int)Math.round(Double.parseDouble(rawInput.get(0).get(1)));                 //TODO: Long lines and MAGIC NUMBERS: REFACTOR
        myEndValue = (int)Math.round(Double.parseDouble(rawInput.get(0).get(2)));
        myIncrement = (int)Math.round(Double.parseDouble(rawInput.get(0).get(3)));
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
