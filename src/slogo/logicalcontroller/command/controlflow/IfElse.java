package slogo.logicalcontroller.command.controlflow;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements the IF-ELSE Factory Design Pattern
 * @author Alex Xu
 */
public class IfElse extends ControlFlowCommand {
    private List<String> falseCommandsList;

    private int myConditional;

    public IfElse(double conditional, List<String> rawInputTrue, List<String> rawInputFalse){
        super(rawInputTrue);
        myConditional = (int)Math.round(conditional);
        falseCommandsList = rawInputFalse;
    }

    @Override
    public void unravelCode() {
        List<String> result;
        if(isTrue()){
            result = this.getBody();
        }
        else{
            result = falseCommandsList;
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
