package slogo.logicalcontroller.command.modifier;

import slogo.exceptions.InvalidCommandException;
import slogo.model.ModelTurtle;

import java.lang.reflect.Method;
import java.util.List;

public class SetHeading extends ModifierCommand {
    public SetHeading(List<String> args){
        super(args.get(0));
    }

    @Override
    public String toString() {
        return "setHeading " + this.argument1;
    }
}
