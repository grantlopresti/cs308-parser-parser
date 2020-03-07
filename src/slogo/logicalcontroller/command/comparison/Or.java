package slogo.logicalcontroller.command.comparison;

import slogo.model.ModelTurtle;

import java.util.List;

public class Or extends ComparisonCommand {

    public Or(List<String> args){
        super(args.get(0), args.get(1));
    }

    @Override
    public String execute(ModelTurtle turtle) {
        boolean bool = this.argument1 != 0 || this.argument2 != 0;
        return booleanToString(bool);
    }
}
