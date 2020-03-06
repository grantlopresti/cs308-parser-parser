package slogo.logicalcontroller.command.teller;

import slogo.logicalcontroller.command.Command;
import slogo.model.ModelCollection;

import java.util.List;

public class Tell extends TellerCommand {

    public Tell(List<String> args) {
        super(args);
    }

    @Override
    public String execute(ModelCollection model) {
        String ret = myArgs.get(myArgs.size()-1);
        System.out.println("executing tell: returning " + ret);
        return ret;
    }

    @Override
    public String getCommandType() {
        return "Tell";
    }
}
