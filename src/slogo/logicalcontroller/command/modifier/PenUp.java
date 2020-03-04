package slogo.logicalcontroller.command.modifier;

import slogo.exceptions.InvalidCommandException;
import slogo.model.ModelTurtle;

import java.lang.reflect.Method;
import java.util.List;

public class PenUp extends ModifierCommand {
    public static final int RETURN_VALUE = 0;

    public PenUp(List<String> args){
        super(""+RETURN_VALUE);
    }

    @Override
    public void execute(ModelTurtle turtle) {
        try {
            String name = this.getMethodName();
            Method method = turtle.getClass().getMethod(name.toLowerCase());
            method.invoke(turtle);
        } catch (Exception e) {
            throw new InvalidCommandException();
        }
    }

    @Override
    public String codeReplace() {
        return "";
    }
}
