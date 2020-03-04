package slogo.logicalcontroller.command.modifier;

import slogo.exceptions.InvalidCommandException;
import slogo.model.ModelTurtle;

import java.lang.reflect.Method;
import java.util.List;

public class SetPosition extends ModifierCommand {
    public SetPosition(List<String> args){
        super(args.get(0), args.get(1));
    }

    @Override
    public void execute(ModelTurtle turtle) {
        try {
            String name = this.getMethodName();
            Method method = turtle.getClass().getMethod(name.toLowerCase(), double.class, double.class);
            Double val1 = this.getArgument1();
            Double val2 = this.getArgument2();
            method.invoke(turtle, val1, val2);
        } catch (Exception e) {
            throw new InvalidCommandException();
        }

    }

    @Override
    public String codeReplace() {
        return "";
    }
}
