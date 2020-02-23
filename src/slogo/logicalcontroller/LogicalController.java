package slogo.logicalcontroller;

import slogo.exceptions.InvalidCommandException;
import slogo.logicalcontroller.parser.Parser;
import slogo.model.ModelCollection;
import slogo.model.ModelObject;
import slogo.visualcontroller.VisualCommand;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Logical controller handles the interaction between the user input from the GUI, the parser, command objects,
 * variables, and changes in the Model package.
 * @author Alex Xu
 */

public class LogicalController {

  private ModelCollection myModelCollection;
  private List<VisualCommand> myVisualCommands;
  private Parser myParser;

  /**
   * Default constructor for the Logical Controller
   */
  public LogicalController(){
    initializeController();
  }

  /**
   * Code that interacts with the GUI, and receives strings as commands
   * Assumption is that there is only one ModelObject (Turtle)
   * @param command
   * @throws InvalidCommandException
   */
  //TODO: Note to self:  Need to change the code below to non-static. Also worry about error handling later.
  public static void handleNewCommand(String command) throws InvalidCommandException {
    System.out.println(command);

    /*
    Command myCurrentCommand = myParser.parse(command);   //TODO: Amjad: Parser.parse() method needs to return a Command (or collection of commands, as implementation here can change easily)
                                                          //TODO: Naming of methods/classes can change and are flexible
    for (Object mo : myModelCollection){
      if(myCurrentCommand.getCommandCategory().equals(VISUAL_COMMAND_NAME){
        myVisualCommands.add(myCurrentCommand);
      }
      else{
        String commandName = myCurrentCommand.getCommandType();
        Method method = mo.getClass().getMethod(commandName, double.class);
        double myValue = myCurrentCommand.getValue();
        method.invoke(mo, myValue);
      }
    }
    */
  }

  /**
   * Returns collection of VisualCommands.
   * @return
   */
  public List<VisualCommand> getVisualCommands(){
    return myVisualCommands;
  }

  /**
   * Returns the collection of ModelObjects.
   * @return
   */
  //TODO: Not to self: change so that it returns a collection of immutable modelobjects instead.
  public ModelCollection getModelCollection(){
    return myModelCollection;
  }

  /**
   * Initializes/Resets the Logical Controller.
   */
  private void initializeController(){
    myModelCollection = new ModelCollection();
    //myParser = new Parser();
    //myVisualCommands = new ArrayList<VisualCommand>();
  }

}