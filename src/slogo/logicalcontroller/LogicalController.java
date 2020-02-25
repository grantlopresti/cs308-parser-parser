package slogo.logicalcontroller;

import slogo.exceptions.InvalidCommandException;
import slogo.logicalcontroller.command.Command;
import slogo.logicalcontroller.command.Parser;
import slogo.model.ModelCollection;
import slogo.model.ModelTurtle;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Logical controller handles the interaction between the user input from the GUI, the parser, command objects,
 * variables, and changes in the Model package.
 * @author Alex Xu and Amjad S.
 */
public class LogicalController {
  private static Parser myParser;
  private static ModelCollection myModelCollection;

  private LogicalController() {}

  /**
   * To be called from the front-end to change the language (also needs to happen the first time).
   * @param language
   * @throws IOException
   */
  public static void setLanguage(String language) throws IOException {
    myParser = new Parser(language);
  }

  /**
   * Code that interacts with the GUI, and receives strings as commands
   * Assumption is that there is only one ModelObject (Turtle)
   * @param command
   * @throws InvalidCommandException
   */
  public static void handleNewCommand(String command) throws InvalidCommandException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    //TODO: Handle input command, try/catch for invalid and route potential error back to
    //System.out.println(command);

    List<String> commandList;
    commandList = Arrays.asList(command.split("\n"));


    myParser.parse(commandList);
    List<Command> commandObjectList = myParser.getCommands();
    Command myCurrentCommand = commandObjectList.get(0);    //TODO: Only one command right now
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

  }

  /**
   * Returns the collection of ModelObjects.
   * @return
   */
  //TODO: Not to self: change in the future so that it returns a collection of immutable modelobjects instead.
   public ModelCollection getModelCollection(){
    return myModelCollection;
   }

  /**
   * Initializes/Resets the Logical Controller.
   */
  public static void initializeController(){
    myModelCollection = new ModelCollection();
    myModelCollection.append(new ModelTurtle());
  }

  /**
   * Overloaded method. Initializes/Resets the Logical Controller.
   * @param language
   */
  public static void initializeController(String language) throws IOException {
    myModelCollection = new ModelCollection();
    myModelCollection.append(new ModelTurtle());

    myParser = new Parser(language);
  }
}
