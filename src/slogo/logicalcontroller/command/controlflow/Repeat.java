package slogo.logicalcontroller.command.controlflow;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements the REPEAT Factory Design Pattern
 * @author Alex Xu
 */
public class Repeat extends ControlFlowCommand {
    private int repeatCount;

    /**
     * Constructor for the Repeat object, takes in the number of repeats desired, rounds to nearest integer.
     * @param repeat
     * @param rawInput
     */
    public Repeat(List<String> args ) {//double repeat, List<String> rawInput){
        super(args.subList(1, args.size()-1));
        repeatCount = (int)Math.round(Double.parseDouble(args.get(0)));
    }

    @Override
    protected void unravelCode() {
        List<String> myBody = this.getBody();
        List<String> result = new ArrayList<>();
        result.add("SET :repcount " + repeatCount);

        for(int i = 0; i<repeatCount; i++){
            result.addAll(myBody);
        }
        setUnraveledCode(result);
    }
}
