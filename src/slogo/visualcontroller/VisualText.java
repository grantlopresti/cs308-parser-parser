package slogo.visualcontroller;

public abstract class VisualText {

    public final String myString;

    public VisualText(String myString) {
        this.myString = myString;
    }

    public String getString() {
        return this.myString;
    }
}
