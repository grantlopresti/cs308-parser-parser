package slogo.visualcontroller;

public abstract class VisualText {

    public final String myString;

    public VisualText(String myString) {
        this.myString = myString;
    }

    @Override
    public String toString() {
        return this.myString;
    }
}
