package slogo.logicalcontroller.command.modifier;

import slogo.exceptions.InvalidCommandException;
import slogo.model.ModelTurtle;

import java.lang.reflect.Method;
import java.util.List;

public class SetTowards extends ModifierCommand {

    public SetTowards(List<String> args){
        super(args.get(0), args.get(1));
    }

    @Override
    public String toString() {
        return "setTowards " + this.argument1 + this.argument2;
    }

    @Override
    public String execute(ModelTurtle turtle) {
        return executeDoubleParameter(turtle);
    }
}
