package slogo.logicalcontroller.command.controlflow;

import java.util.List;

public abstract class ToAbs implements ToInterface {

    public static final String SEPARATOR = ":";

    protected String myName;
    protected List<String> myValue;

    public ToAbs(String name, List<String> value) {
        this.myName = name;
        this.myValue = value;
    }

    @Override
    public String getName() {return this.myName;}

    @Override
    public List<String> getValue() {return this.myValue;}
}
