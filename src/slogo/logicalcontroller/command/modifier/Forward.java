package slogo.logicalcontroller.command.modifier;

import slogo.exceptions.InvalidCommandException;
import slogo.model.ModelTurtle;

import java.lang.reflect.Method;
import java.util.List;

public class Forward extends ModifierCommand {

    public Forward(List<String> args){
        super(args.get(0));
        System.out.printf("making forward with arg: %s \n", this.getArgument1());
    }

    @Override
    public String toString() {
        return "fd " + this.argument1;
    }

}
