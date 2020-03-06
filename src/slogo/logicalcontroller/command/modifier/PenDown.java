package slogo.logicalcontroller.command.modifier;

import java.util.List;

public class PenDown extends ModifierCommand {
    private static final int RETURN_VALUE = 1;

    public PenDown(List<String> args){
        super(Integer.toString(RETURN_VALUE));
    }

    @Override
    public String toString() {
        return "penDown";
    }
}
