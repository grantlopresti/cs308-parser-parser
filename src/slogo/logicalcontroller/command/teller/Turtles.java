package slogo.logicalcontroller.command.teller;

import slogo.logicalcontroller.command.Command;
import slogo.model.ModelCollection;
import slogo.model.ModelObject;
import slogo.model.ModelTurtle;

import java.util.List;

public class Turtles extends TellerCommand {

    public Turtles(List<String> args){
        super(args);
    }

    @Override
    public String execute(ModelCollection model) {
        int count = 0;
        for (ModelObject object: model.getModelMap().values()) {
            count ++;
        }
        return Integer.toString(count);
    }

    @Override
    public String getCommandType() {
        return "Turtles";
    }

}
