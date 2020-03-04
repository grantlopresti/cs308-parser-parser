package slogo.logicalcontroller.command.modifier;

import java.util.List;

public class SetPosition extends ModifierCommand {
    public SetPosition(List<String> args){
        super(args.get(0), args.get(1));
    }

    @Override
    public String execute() {
        return null;
    }
}
