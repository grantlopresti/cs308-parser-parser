package slogo.logicalcontroller;

import slogo.exceptions.InvalidCommandException;
import slogo.model.ModelObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Logical controller handles the interaction between the user input from the GUI, the parser, command objects,
 * variables, and changes in the Model package.
 * @author Alex Xu
 */

public class LogicalController {

  private static List<ModelObject> modelObjectList;
  //private parser myParser;

  private LogicalController(){}

  /**
   * Initializes the Logical Controller. Should only be called once at the start of the program.
   */
  public static void initializeController(){
    modelObjectList = new ArrayList<ModelObject>();
  }

  /**
   * Code that interacts with the GUI, and receives strings as commands
   * @param command
   * @throws InvalidCommandException
   */
  //TODO: May need to discuss the code below.
  public static void handleNewCommand(String command) throws InvalidCommandException {
    //TODO: Handle input command, try/catch for invalid and route potential error back to
    System.out.println(command);

    //Command myCurrentCommand = myParser.parse(command);

    //Then, get the command type and use reflection to call methods in on the List of ModelObjects.


  }

}