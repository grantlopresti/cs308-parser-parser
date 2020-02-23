package slogo.logicalcontroller;

import slogo.exceptions.InvalidCommandException;

/**
 * Logical controller handles the interaction between the user input from the GUI, the parser, command objects,
 * variables, and changes in the Model package.
 * @author Alex Xu
 */

public class LogicalController {

  public LogicalController(){

  }

  public static void handleNewCommand(String command) throws InvalidCommandException {
    //TODO: Handle input command, try/catch for invalid and route potential error back to
    System.out.println(command);
  }

}
