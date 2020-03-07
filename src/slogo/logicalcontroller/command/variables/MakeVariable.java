package slogo.logicalcontroller.command.variables;

import slogo.logicalcontroller.variable.BasicVariable;
import slogo.logicalcontroller.variable.Variable;
import slogo.logicalcontroller.variable.VariableList;

import java.util.Iterator;
import java.util.List;


/**
 * Comamnd to make variables. Assumption is that new variables/updated variables will always appear last on the list.
 * @author Alex Xu
 */
public class MakeVariable {
    private double myValue;
    private String myName;

    public MakeVariable(List<String> args) {
        String name = args.get(0);
        double value = Double.parseDouble(args.get(1));
        // Variable myVariable = new BasicVariable(name, value);
        this.myValue = value;
        this.myName = name;
        System.out.printf("successfully created variable with name: %s and value %.2f", name, value);
    }

    public double getMyValue(){
        return myValue;
    }

    public String execute(VariableList list) {
        for (Object o : list) {
            Variable v = (Variable) o;
            if (v.getName().equals(this.myName)) {
                ;
            }
        }
        return "";
    }

}
