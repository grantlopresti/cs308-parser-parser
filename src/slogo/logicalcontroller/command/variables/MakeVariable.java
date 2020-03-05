package slogo.logicalcontroller.command.variables;

import slogo.logicalcontroller.variable.BasicVariable;
import slogo.logicalcontroller.variable.Variable;
import slogo.logicalcontroller.variable.VariableList;


/**
 * Comamnd to make variables. Assumption is that new variables/updated variables will always appear last on the list.
 * @author Alex Xu
 */
public class MakeVariable {
    VariableList myVariableList;
    double returnValue;

    public MakeVariable(String name, double value, VariableList variableList){
        Variable myVariable = new BasicVariable(name, value);
        myVariableList = variableList;
        returnValue = value;
        myVariableList.addVariable(myVariable);
    }

    public VariableList getUpdatedVariableList(){
        return myVariableList;
    }

    public double getReturnValue(){
        return returnValue;
    }

}
