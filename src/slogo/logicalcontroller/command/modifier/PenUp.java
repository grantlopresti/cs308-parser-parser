package slogo.logicalcontroller.command.modifier;

import java.util.List;

public class PenUp extends ModifierCommand {
    private static final int RETURN_VALUE = 0;

    public PenUp(List<String> args){
        super(Integer.toString(RETURN_VALUE));
        // System.out.println("successfully created penup command");
    }

    @Override
    public String toString() {
        return "penUp";
    }
}
