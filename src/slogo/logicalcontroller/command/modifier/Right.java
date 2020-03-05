package slogo.logicalcontroller.command.modifier;

import slogo.exceptions.InvalidCommandException;
import slogo.model.ModelTurtle;

import java.lang.reflect.Method;
import java.util.List;

public class Right extends ModifierCommand {

    public Right(List<String> args){
        super(args.get(0));
    }

    @Override
    public String toString() {
        return "right " + this.argument1;
    }
}
