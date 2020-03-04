package slogo.logicalcontroller.command.controlflow;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements the IF Factory Design Pattern
 * @author Alex Xu
 */
public class If extends ControlFlowCommand {
    private int myConditional;

    public If(double conditional, List<String> rawInput){
        super(rawInput);
        myConditional = (int)Math.round(conditional);
    }

    @Override
    public void unravelCode() {
        List<String> result;
        if(isTrue()){
            result = this.getBody();
        }
        else{
            result = new ArrayList<>();
            result.add("0");                            //TODO: Note to self: Magic Number - Is this needed? -Alex
        }
        setUnraveledCode(result);
    }

    private boolean isTrue(){
        if(myConditional == 0){
            return false;
        }
        else{
            return true;
        }
    }
}
