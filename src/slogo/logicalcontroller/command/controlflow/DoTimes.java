package slogo.logicalcontroller.command.controlflow;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements the DOTIMES Factory Design Pattern
 * @author Alex Xu
 */
public class DoTimes extends ControlFlowCommand {
    private int repeatCount;

    public DoTimes(int argument, List<String> rawInput){
        super(rawInput);
        repeatCount = argument;
    }

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