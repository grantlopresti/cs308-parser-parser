package slogo.visualcontroller;

public class VisualData {

    private static final String SEPARATOR = " ";

    private final String myName;
    private final double myValue;

    public VisualData(String name, double value) {
        myName = name;
        myValue = value;
    }

    public String getName() {return myName;}
    public double getValue() {return myValue;}
    public String toString() {return myName + SEPARATOR + myValue;}

}
