package slogo.logicalcontroller.command.variables;

import slogo.logicalcontroller.variable.BasicVariable;
import slogo.logicalcontroller.variable.Variable;
import slogo.logicalcontroller.variable.VariableList;

import java.util.List;


/**
 * Comamnd to make variables. Assumption is that new variables/updated variables will always appear last on the list.
 * @author Alex Xu
 */
public class MakeVariable extends VariableCommand{
    private VariableList myVariableList;
    private double returnValue;

    public MakeVariable(String name, double value, VariableList variableList){
        Variable myVariable = new BasicVariable(name, value);
        myVariableList = variableList;
        returnValue = value;
        myVariableList.addVariable(myVariable);
    }

    public MakeVariable(List<String> args) {
        for (String s: args) {
            System.out.println(s);
        }
        String name = args.get(0);
        double value = Double.parseDouble(args.get(1));
        Variable myVariable = new BasicVariable(name, value);
        this.returnValue = value;
        System.out.printf("successfully created variable with name: %s and value %.2f", name, value);
    }

    public VariableList getUpdatedVariableList(){
        return myVariableList;
    }

    public double getReturnValue(){
        return returnValue;
    }

}
