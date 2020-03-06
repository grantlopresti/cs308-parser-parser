package slogo.logicalcontroller.command.comparison;

import java.util.List;

public class Not extends ComparisonCommand {

    public Not(List<String> args){
        super(args.get(0));
    }

    @Override
    public String execute() {
        boolean bool = this.argument1 == 0;
        return booleanToString(bool);
    }
}
