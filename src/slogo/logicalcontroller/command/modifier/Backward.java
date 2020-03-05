package slogo.logicalcontroller.command.modifier;

import java.util.List;

public class Backward extends ModifierCommand {

    public Backward(List<String> args){
        super(args.get(0));
    }

    @Override
    public String toString() {
        return "back " + this.argument1;
    }
}
