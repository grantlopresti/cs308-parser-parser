package slogo.logicalcontroller.command.variables;

import slogo.logicalcontroller.variable.Variable;
import slogo.logicalcontroller.variable.VariableList;

/**
 * Command to evaluate variables
 * @author Amjad S.
 */
public class EvalulateVariable {

    double varToBeRet;

    public EvalulateVariable(String name, VariableList variableList){

        for(Object v: variableList){
            if(variableList.isSameVariable(name, (Variable) v)){
                varToBeRet = ((Variable) v).getValue();
            }
        }
    }

    public double getReturnValue(){
        return this.varToBeRet;
    }
}
