package slogo.logicalcontroller;

import slogo.exceptions.InvalidCommandException;
import slogo.exceptions.LogicalException;
import slogo.logicalcontroller.command.Command;
import slogo.logicalcontroller.variable.BasicVariable;
import slogo.logicalcontroller.variable.Variable;
import slogo.model.ModelCollection;
import slogo.model.ModelObject;
import slogo.model.ModelTurtle;
import slogo.visualcontroller.VisualController;

import javax.script.ScriptException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * Logical controller handles the interaction between the user input from the GUI, the parser, command objects,
 * variables, and changes in the Model package.
 * @author Alex Xu
 */
public class LogicalController {
  private Parser myParser;
  private static final String DEFAULT_LANGUAGE = "ENGLISH";

  private ModelCollection myModelCollection;
  private VisualController myVisualController;
  private List<Variable> myVariables;

  public LogicalController(ModelCollection modelCollection, VisualController visualController, List<Variable> variables){
    myModelCollection = modelCollection;
    myVisualController = visualController;
    myModelCollection.append(new ModelTurtle());
    myVisualController.moveModelObject(myModelCollection);
    myVariables = variables;
    try {
      this.setLanguage(DEFAULT_LANGUAGE);
    } catch (Exception e) {
      System.exit(0);
    }
  }

  /**
   * To be called from the front-end to change the language (also needs to happen the first time).
   * @param language
   * @throws IOException
   */
  public void setLanguage(String language) throws IOException {
    this.myParser = new Parser(language);
  }

  /**
   * Code that interacts with the GUI, and receives strings as commands
   * Assumption is that there is only one ModelObject (Turtle)
   * @param command
   * @throws InvalidCommandException
   */
  public void handleNewCommand(String command) throws InvalidCommandException, NoSuchMethodException, InstantiationException, ScriptException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
    //System.out.println(command);

    // STEP 1: Parse all commands from the input String
    this.myParser.parse(Arrays.asList(command.split("\n")));

    // STEP 2: Fetch final commands from the parser
    List<Command> commandList = this.myParser.getCommands();

    // STEP 3: Execute individual commands on each turtle object using reflection
    for (Object turtle : myModelCollection){
      for(Command thisCommand : commandList) {
        ModelTurtle modelTurtle = (ModelTurtle) turtle;
        printTurtleState(modelTurtle, "Before");
        String commandName = thisCommand.getCommandType();
        Method method = turtle.getClass().getMethod(commandName.toLowerCase(), double.class);
        double myValue = thisCommand.getValue();
        method.invoke(turtle, myValue);
        System.out.println("Command Executed: " + commandName);
        printTurtleState(modelTurtle, "After");
        myVisualController.moveModelObject(myModelCollection);
        myVisualController.updateCommands(command);
      }
    }
    // testLogic(command);
  }

  private void testLogic(String command) {
    for(Object turtle : myModelCollection){
      ModelTurtle myModelTurtle = (ModelTurtle) turtle;
      myModelTurtle.move(100);
      myModelTurtle.turn(90);
    }
    myVisualController.moveModelObject(myModelCollection);
    myVisualController.updateCommands(command);
    //myVisualController.updateErrors(new InvalidCommandException("Testing Error (thrown from "
    //    + "Logical Controller)"));
    //myVisualController.updateVariables(new BasicVariable("guy", 2));
  }

  private void printTurtleState(ModelTurtle turtle, String seq) {
    System.out.println(seq + " Y " + turtle.getY());
    System.out.println(seq + " X " + turtle.getX());
  }

}