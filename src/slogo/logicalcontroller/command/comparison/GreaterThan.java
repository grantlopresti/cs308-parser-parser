package slogo.logicalcontroller.command.comparison;

import slogo.logicalcontroller.command.Command;

import java.util.List;

public class GreaterThan extends ComparisonCommand {

    public GreaterThan(List<String> args){
        super(args.get(0), args.get(1));
    }

    @Override
    public String execute() {
        boolean bool = this.argument1 > this.argument2;
        return Boolean.toString(bool);
    }

}
