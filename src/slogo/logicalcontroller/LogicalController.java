package slogo.logicalcontroller;

import slogo.exceptions.InvalidCommandException;
import slogo.logicalcontroller.command.Command;
import slogo.logicalcontroller.command.Parser;
import slogo.logicalcontroller.variable.Variable;
import slogo.model.ModelCollection;
import slogo.model.ModelObject;
import slogo.model.ModelTurtle;
import slogo.visualcontroller.VisualController;

import javax.script.ScriptException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Logical controller handles the interaction between the user input from the GUI, the parser, command objects,
 * variables, and changes in the Model package.
 * @author Alex Xu and Amjad S.
 */
public class LogicalController {
  private Parser myParser;

  private ModelCollection myModelCollection;
  private List<Command> myCommandList;
  private List<Variable> myVariableList;

  public LogicalController(){}

  /**
   * To be called from the front-end to change the language (also needs to happen the first time).
   * @param language
   * @throws IOException
   */
  public void setLanguage(String language) throws IOException {
    myParser = new Parser(language);
  }

  /**
   * Code that interacts with the GUI, and receives strings as commands
   * Assumption is that there is only one ModelObject (Turtle)
   * @param command
   * @throws InvalidCommandException
   */
  public void handleNewCommand(String command) throws InvalidCommandException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, IOException, ScriptException {
    //TODO: Handle input command, try/catch for invalid and route potential error back to
    initializeController("English");
    System.out.println(command);

    List<String> commandList;
    commandList = Arrays.asList(command.split("\n"));


    myParser.parse(commandList);
    List<Command> commandObjectList = myParser.getCommands();

    for (Object mo : myModelCollection){
      for(Command myCurrentCommand : commandObjectList) {
        //Command myCurrentCommand = commandObjectList.get(0);
        ModelObject myModelObject = (ModelObject) mo;

        System.out.println("Before Y " + myModelObject.getY());
        System.out.println("Before X " + myModelObject.getX());

        String commandName = myCurrentCommand.getCommandType();
        //Class parameterClass = Class.forName("double");
        Method method = myModelObject.getClass().getMethod(commandName.toLowerCase(), double.class);
        double myValue = myCurrentCommand.getValue();
        method.invoke(myModelObject, myValue);

        System.out.println("Command Executed: " + commandName);
        System.out.println("After Y: " + myModelObject.getY());
        System.out.println("After X: " + myModelObject.getX());

      }
    }

    //passToVisualController(myModelCollection, myCommandList, myVariableList);


  }

  private void passToVisualController(){
    //VisualController.(myModelCollection, myCommandList, myVariableList);
  }

  /**
   * Initializes/Resets the Logical Controller.
   */
  public void initializeController(){
    myModelCollection = new ModelCollection();
    myModelCollection.append(new ModelTurtle());
  }

  /**
   * Overloaded method. Initializes/Resets the Logical Controller. Should be called before logical controller can be used.
   * @param language
   */
  public void initializeController(String language) throws IOException {
    myModelCollection = new ModelCollection();
    myModelCollection.append(new ModelTurtle());

    setLanguage(language);

    myCommandList = new ArrayList<>();
    myVariableList = new ArrayList<>();
  }

}
