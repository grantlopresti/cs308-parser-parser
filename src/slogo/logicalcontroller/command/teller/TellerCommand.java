package slogo.logicalcontroller.command.teller;

import slogo.logicalcontroller.command.Command;
import slogo.model.ModelCollection;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class for TellerCommand
 * @author Math Smith
 */
public abstract class TellerCommand implements Command {

    protected List<Integer> myArgs;

    public TellerCommand(List<String> args) {
        myArgs = new ArrayList<Integer>();
        System.out.println("Attempting to construct teller command from arguments list: ");
        for (String s: args) {
            System.out.printf("attempting to add string (%s) to args list\n", s);
            this.myArgs.add(Integer.parseInt(s));
        }
    }

    public abstract String execute(ModelCollection model);

}
