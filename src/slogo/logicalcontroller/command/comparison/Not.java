package slogo.logicalcontroller.command.comparison;

import slogo.model.ModelTurtle;

import java.util.List;

public class Not extends ComparisonCommand {

    public Not(List<String> args){
        super(args.get(0));
    }

    @Override
    public String execute(ModelTurtle turtle) {
        boolean bool = this.argument1 == 0;
        return booleanToString(bool);
    }
}
