package slogo.logicalcontroller.command.teller;

import slogo.logicalcontroller.command.Command;
import slogo.model.ModelCollection;

import java.util.List;

public class Ask extends TellerCommand {

    public Ask(List<String> args){
        super(args);
    }

    @Override
    public String getCommandType() {
        return "Ask";
    }

    @Override
    public String execute(ModelCollection model) {
        return null;
    }
}
