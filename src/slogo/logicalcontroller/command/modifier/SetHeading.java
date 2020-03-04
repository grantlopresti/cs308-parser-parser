package slogo.logicalcontroller.command.modifier;

import java.util.List;

public class SetHeading extends ModifierCommand {
    public SetHeading(List<String> args){
        super(args.get(0));
    }
}
