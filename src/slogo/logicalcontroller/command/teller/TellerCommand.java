package slogo.logicalcontroller.command.teller;

import slogo.logicalcontroller.command.Command;
import slogo.model.ModelCollection;

import java.util.List;

/**
 * Abstract class for TellerCommand
 * @author Math Smith
 */
public abstract class TellerCommand implements Command {

    protected List<String> myArgs;

    public TellerCommand(List<String> args) {
        this.myArgs = args;
    }

    public abstract String execute(ModelCollection model);

}
