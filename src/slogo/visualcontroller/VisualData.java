package slogo.visualcontroller;

public class VisualData {

    private static final String SEPARATOR = " ";

    private final String myName;
    private final double myValue;

    public VisualData(String name, double value) {
        this.myName = name;
        this.myValue = value;
    }

    public String getName() {return this.myName;}
    public double getValue() {return this.myValue;}
    public String toString() {return this.myName + SEPARATOR + this.myValue;}

}
