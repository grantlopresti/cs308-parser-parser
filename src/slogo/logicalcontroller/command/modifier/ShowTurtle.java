package slogo.logicalcontroller.command.modifier;

import java.util.List;

public class ShowTurtle extends ModifierCommand {
    public static final int RETURN_VALUE = 1;

    public ShowTurtle(List<String> args){
        super(""+RETURN_VALUE);
    }

    @Override
    public String execute() {
        return null;
    }
}
