package slogo.logicalcontroller.command.modifier;

import slogo.exceptions.InvalidCommandException;
import slogo.model.ModelTurtle;

import java.lang.reflect.Method;
import java.util.List;

public class ShowTurtle extends ModifierCommand {
    public static final int RETURN_VALUE = 1;

    public ShowTurtle(List<String> args){
        super(""+RETURN_VALUE);
    }

    @Override
    public String toString() {
        return "showTurtle";
    }

    @Override
    public void execute(ModelTurtle turtle) {
        try {
            String name = this.getMethodName();
            Method method = turtle.getClass().getMethod(name.toLowerCase(), double.class);
            Double value = this.getArgument1();
            method.invoke(turtle, value);
        } catch (Exception e) {
            throw new InvalidCommandException();
        }

    }

    @Override
    public String codeReplace() {
        return "";
    }
}
