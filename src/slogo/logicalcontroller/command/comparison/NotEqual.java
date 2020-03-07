package slogo.logicalcontroller.command.comparison;


import slogo.model.ModelTurtle;

import java.util.List;

public class NotEqual extends ComparisonCommand {

    public NotEqual(List<String> args){
        super(args.get(0), args.get(1));
    }

    @Override
    public String execute(ModelTurtle turtle) {
        boolean bool = this.argument1 != this.argument2;
        return Boolean.toString(bool);
    }
}
