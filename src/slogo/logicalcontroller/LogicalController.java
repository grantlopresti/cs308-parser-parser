package slogo.logicalcontroller;

import slogo.exceptions.InvalidCommandException;

public class LogicalController {

  public LogicalController(){

  }

  public static void handleNewCommand(String command) throws InvalidCommandException {
    //TODO: Handle input command, try/catch for invalid and route potential error back to
    System.out.println(command);
  }

}
