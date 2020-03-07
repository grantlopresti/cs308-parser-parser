package slogo.logicalcontroller.command.teller;

import slogo.model.ModelCollection;
import slogo.model.ModelTurtle;

import java.util.List;

public class ID extends TellerCommand {

    private static final String DEFAULT_ID = "0";

    public ID(List<String> args) {
        super(args);
    }

    @Override
    public String execute(ModelCollection model) {
        return DEFAULT_ID;
    }

    @Override
    public String getCommandType() {
        return "ID";
    }

    @Override
    public String execute(ModelTurtle turtle) {
        return null;
    }
}
