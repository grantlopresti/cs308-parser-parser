package slogo.logicalcontroller.command.variables;

import slogo.exceptions.VariableDoesNotExistException;
import slogo.logicalcontroller.variable.Variable;
import slogo.logicalcontroller.variable.VariableList;

/**
 * Command to evaluate variables
 * @author Alex X. and Amjad S.
 */
public class EvaluateVariable extends VariableCommand{

    private double variableValue;
    private VariableList myVariableList;
    private String myName;
    private boolean variableExists;

    public EvaluateVariable(String name, VariableList variableList){
        myName = name;
        myVariableList = variableList;
        lookForSameVariable();
    }

    private void lookForSameVariable(){
        for(Object v: myVariableList){
            if(myVariableList.isSameVariable(myName, (Variable) v)){
                variableValue = ((Variable) v).getValue();
                variableExists = true;
                return;
            }
        }
        variableExists = false;
    }

    public double getReturnValue(){
        if(variableExists){
            return this.variableValue;
        }
        else {
            throw new VariableDoesNotExistException();
        }
    }
}
