package slogo.logicalcontroller.variable;

/**
 * Abstract variable class with default variable behaviors, to be over-ridden by concrete variable subclasses.
 * @author Alex Xu
 */
public abstract class Variable implements VariableInterface {

    private static final String SEPARATOR = " : ";

    protected String myName;
    protected double myValue;

    public Variable(String name, double value) {
        this.myName = name;
        this.myValue = value;
    }

    @Override
    public String getName() {return this.myName;}

    @Override
    public double getValue() {return this.myValue;}

}
