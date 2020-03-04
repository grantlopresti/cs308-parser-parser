package slogo.logicalcontroller.command.modifier;

import java.util.List;

public class Right extends ModifierCommand {

    public Right(List<String> args){
        super(args.get(0));
    }
}
