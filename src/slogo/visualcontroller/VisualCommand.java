package slogo.visualcontroller;

import slogo.logicalcontroller.command.Command;

public class VisualCommand extends VisualText{

    public VisualCommand(Command command) {
        // super("FIX ME (in VisualCommand)");
        super(command.toString());
    }

    public VisualCommand(String fullUserInput) {
        super(fullUserInput);
    }
}
