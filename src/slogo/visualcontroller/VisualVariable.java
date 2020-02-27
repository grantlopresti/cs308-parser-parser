package slogo.visualcontroller;

import slogo.logicalcontroller.variable.Variable;

public class VisualVariable {

    private static final String SEPARATOR = " --> ";

    private final String myName;
    private final double myValue;

    public VisualVariable(Variable v) {
        myName = v.getName();
        myValue = v.getValue();
    }
    @Override
    public String toString() {return this.myName + SEPARATOR + this.myValue;}
}
