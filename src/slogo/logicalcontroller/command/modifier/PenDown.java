package slogo.logicalcontroller.command.modifier;

import java.util.List;

public class PenDown extends ModifierCommand {
    public static final int RETURN_VALUE = 1;

    public PenDown(List<String> args){
        super(""+RETURN_VALUE);
    }
}
