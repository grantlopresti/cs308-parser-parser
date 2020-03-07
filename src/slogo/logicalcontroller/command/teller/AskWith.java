package slogo.logicalcontroller.command.teller;

import slogo.logicalcontroller.command.Command;
import slogo.model.ModelCollection;
import slogo.model.ModelTurtle;

import java.util.List;

public class AskWith extends TellerCommand {

    public AskWith(List<String> args){
        super(args);
    }

    @Override
    public String execute(ModelCollection model) {
        return null;
    }

    @Override
    public String getCommandType() {
        return "AskWidth";
    }


}
