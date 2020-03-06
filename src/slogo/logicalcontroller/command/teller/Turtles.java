package slogo.logicalcontroller.command.teller;

import slogo.logicalcontroller.command.Command;
import slogo.model.ModelCollection;

import java.util.List;

public class Turtles extends TellerCommand {

    public Turtles(List<String> args){
        super(args);
    }

    @Override
    public String execute(ModelCollection model) {
        return null;
    }

    @Override
    public String getCommandType() {
        return "Turtles";
    }
}
