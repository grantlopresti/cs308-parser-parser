package slogo.logicalcontroller.command.modifier;

import java.util.List;

public class SetTowards extends ModifierCommand {

    public SetTowards(List<String> args){
        super(args.get(0), args.get(1));
    }
}
