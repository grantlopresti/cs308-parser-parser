package slogo.logicalcontroller.command.controlflow;

import slogo.model.ModelTurtle;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements the IF-ELSE Factory Design Pattern
 * @author Alex Xu
 */
public class IfElse extends ControlFlowCommand {
    private List<String> falseCommandsList;

    private int myConditional;

    public IfElse(List<List<String>> rawInput){
        super(rawInput.get(1));
        myConditional = (int)Math.round(Double.parseDouble(rawInput.get(0).get(0)));
        falseCommandsList = rawInput.get(2);
    }

    @Override
    protected void unravelCode() {
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

    @Override
    public String execute(ModelTurtle turtle) {
        return null;
    }
}
