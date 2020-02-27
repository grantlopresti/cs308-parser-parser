package slogo.visualcontroller;

public abstract class VisualText {

    public final String myString;

    public VisualText(String string) {
        myString = string;
    }

    @Override
    public String toString() {
        return myString;
    }

}
