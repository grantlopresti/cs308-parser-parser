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
public class MakeVariable extends VariableCommand{

    private String myName;
    private double myValue;

    public MakeVariable(List<String> args) {
        String name = args.get(0);
        double value = Double.parseDouble(args.get(1));
        this.myValue = value;
        this.myName = name;
        // System.out.printf("successfully created variable with name: %s and value %.2f", name, value);
    }

    private void appendList(VariableList list) {
        for (Object o : list) {
            Variable v = (Variable) o;
            if (v.getName().equals(this.myName)) {
                list.addVariable(new BasicVariable(this.myName, this.myValue));
                return;
            }
        }
    }

    @Override
    public String execute(VariableList list) {
        appendList(list);
        return Double.toString(this.myValue);
    }

}
