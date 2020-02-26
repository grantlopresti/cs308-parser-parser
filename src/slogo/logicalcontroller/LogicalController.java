package slogo.logicalcontroller;

import slogo.exceptions.InvalidCommandException;
import slogo.logicalcontroller.command.Command;
import slogo.logicalcontroller.command.Parser;
import slogo.logicalcontroller.variable.Variable;
import slogo.model.ModelCollection;
import slogo.visualcontroller.VisualController;

import javax.script.ScriptException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

/**
 * Logical controller handles the interaction between the user input from the GUI, the parser, command objects,
 * variables, and changes in the Model package.
 * @author Alex Xu
 */
public class LogicalController {
  private Parser myParser;

  private ModelCollection myModelCollection;
  private VisualController myVisualController;
  private List<Variable> myVariables;

  public LogicalController(ModelCollection modelCollection, VisualController visualController, List<Variable> variables){
    this.myModelCollection = modelCollection;
    this.myVisualController = visualController;
    this.myVariables = variables;
  }

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
    System.out.println(command);

    List<String> commandList;
    commandList = Arrays.asList(command.split("\n"));

    myParser.set(commandList, myModelCollection, myVariables);

    while(!myParser.isFinished()){
      myParser.executeNextCommand();
      myModelCollection = myParser.getModel();
      Command currentCommand = myParser.getCommand();
      passToVisualController(currentCommand);
    }

    /*
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
    */
    //passToVisualController(myModelCollection, myCommandList, myVariableList);
  }

  private void passToVisualController(Command command){
    myVisualController.moveModelObject(myModelCollection, command);
  }
}