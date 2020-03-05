package slogo.visualcontroller;

public abstract class VisualText {

    private final String myString;

    public VisualText(String string) {
        this.myString = string;
    }

    @Override
    public String toString() {
        return this.myString;
    }

}
