package slogo.visualcontroller;

import slogo.logicalcontroller.variable.Variable;

public class VisualVariable {

    private final String myName;
    private final double myValue;

    public VisualVariable(Variable v) {
        myName = v.getName();
        myValue = v.getValue();
    }
}
