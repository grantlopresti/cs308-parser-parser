package slogo.logicalcontroller.command.comparison;

import java.util.List;

public class LessThan extends ComparisonCommand {

    public LessThan(List<String> args){
        super(args.get(0), args.get(1));
    }

    @Override
    public String execute() {
        boolean bool = this.argument1 < this.argument2;
        return booleanToString(bool);
    }

}
