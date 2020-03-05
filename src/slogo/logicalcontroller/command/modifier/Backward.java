package slogo.logicalcontroller.command.modifier;

import slogo.exceptions.InvalidCommandException;
import slogo.model.ModelTurtle;

import java.lang.reflect.Method;
import java.util.List;

public class Backward extends ModifierCommand {

    public Backward(List<String> args){
        super(args.get(0));
    }

    @Override
    public String toString() {
        return "back " + this.argument1;
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
        return Double.toString(this.argument1);
    }
}
