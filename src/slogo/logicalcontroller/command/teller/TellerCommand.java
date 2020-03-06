package slogo.logicalcontroller.command.teller;

import slogo.logicalcontroller.command.Command;
import slogo.model.ModelCollection;

import java.util.List;

/**
 * Abstract class for TellerCommand
 * @author Math Smith
 */
public abstract class TellerCommand implements Command {

    protected List<Integer> myArgs;

    public TellerCommand(List<String> args) {
        for (String s: args) {
            this.myArgs.add(Integer.parseInt(s));
        }
    }

    public abstract String execute(ModelCollection model);

}
