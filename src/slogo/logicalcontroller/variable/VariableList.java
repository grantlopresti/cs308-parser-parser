package slogo.logicalcontroller.variable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Provides encapsulation for the variable list.
 * Provides only the functionality that is required of it (trade off is less freedom for other classes to manipulate it).
 * @author Alex Xu
 */
public class VariableList implements Iterable{
    private List<Variable> myVariablesList;

    public VariableList(){
        this.myVariablesList = new ArrayList<Variable>();
    }

    /**
     * Gets a Variable at the index.
     * @param index
     * @return
     */
    public Variable get(int index){
        return myVariablesList.get(index);
    }

    /**
     * Add/Update a Variable
     */
    public void addVariable(Variable variable){
        deleteVariable(variable.getName());
        myVariablesList.add(variable);
    }

    /**
     * Deletes a variable by index.
     * @param index
     */
    public void deleteVariable(int index){
        myVariablesList.remove(index);
    }

    /**
     * Deletes a variable by name.
     * @param name
     */
    public void deleteVariable(String name){
        for(Variable v : myVariablesList){
            if(isSameVariable(name, v)){
                myVariablesList.remove(v);
            }
        }
    }

    /**
     * Deletes all variables (clear).
     */
    public void deleteAll(){
        myVariablesList = new ArrayList<>();
    }

    public boolean isSameVariable(String variableName, Variable variable){
       return variableName.equals(variable.getName());
    }

    public boolean isEmpty() {
        return this.myVariablesList.size() == 0;
    }

    @Override
    public Iterator iterator() {
        return myVariablesList.iterator();
    }

}