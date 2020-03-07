package slogo.logicalcontroller.command.controlflow;

import slogo.model.ModelTurtle;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements the DOTIMES Factory Design Pattern
 * @author Alex Xu
 */
public class DoTimes extends ControlFlowCommand {
    private int repeatCount;
    private String variableName;

    /**
     * Constructor for the DOTIMES command. Takes in a number of repeats (rounded to nearest integer)
     * @param rawInput
     */
    public DoTimes(List<List<String>> rawInput){
        super(rawInput.get(1));
        repeatCount = (int)Math.round(Double.parseDouble(rawInput.get(0).get(1)));
        variableName = rawInput.get(0).get(0);

        unravelCode();
    }

    @Override
    protected void unravelCode() {
        List<String> myBody = this.getBody();
        List<String> result = new ArrayList<>();

        for(int i = 0; i<repeatCount; i++){
            //result.add("set :" + variableName + " " + (i+1));
            result.addAll(myBody);
        }
        setUnraveledCode(result);
    }

    @Override
    public String execute(ModelTurtle turtle) {
        return null;
    }
}