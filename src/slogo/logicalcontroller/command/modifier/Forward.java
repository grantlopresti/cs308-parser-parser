package slogo.logicalcontroller.command.modifier;

import java.util.List;

public class Forward extends ModifierCommand {

    public Forward(List<String> args){
        super(args.get(0));
    }
}
