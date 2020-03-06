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
    public String toString() {
        return "setPosition " + this.argument1 + " " + this.argument2;
    }

    @Override
    public String execute(ModelTurtle turtle) {
        return executeDoubleParameter(turtle);
    }
}
