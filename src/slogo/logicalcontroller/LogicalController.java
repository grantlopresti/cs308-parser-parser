package slogo.logicalcontroller;

import slogo.exceptions.InvalidCommandException;
import slogo.logicalcontroller.command.Command;
import slogo.logicalcontroller.command.modifier.Forward;
import slogo.logicalcontroller.variable.BasicVariable;
import slogo.logicalcontroller.variable.Variable;
import slogo.model.ModelCollection;
import slogo.model.ModelTurtle;
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
  private static final String DEFAULT_LANGUAGE = "ENGLISH";

  private Parser myParser;

  private ModelCollection myModelCollection;
  private VisualController myVisualController;
  private List<Variable> myVariables;

  public LogicalController(ModelCollection modelCollection, VisualController visualController, List<Variable> variables){
    myModelCollection = modelCollection;
    myVisualController = visualController;
    // TODO - update visualController initial state to empty lists to get first turtle to show
    // myVisualController.moveModelObject(myModelCollection);
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
    this.myParser = new Parser(language);                         //TODO: Might need to change.
  }

  /**
   * Code that interacts with the GUI, and receives strings as commands
   * Assumption is that there is only one ModelObject (Turtle)
   * @param command
   * @throws InvalidCommandException
   */
  public void handleNewCommand(String command) throws InvalidCommandException, NoSuchMethodException, InstantiationException, ScriptException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
    myParser.parse(Arrays.asList(command.split("\n")));
    while(!myParser.isFinished()){
      myParser.executeNextCommand();
      Command latestCommand = myParser.getLatestCommand();
      ModelCollection newModel = myParser.getModel();
      List<Variable> newVariables = myParser.getVariables();

      myVisualController.update(newModel, newVariables, latestCommand);
    }
  }

  private void testLogic(String command) {
    for(Object turtle : myModelCollection){
      ModelTurtle myModelTurtle = (ModelTurtle) turtle;
      myModelTurtle.move(100);
      myModelTurtle.turn(90);
    }
    // TODO - test and verify that this works
    myVisualController.update(myModelCollection, List.of(new BasicVariable("guy", 2)), new Forward("50"));
    /*
    myVisualController.moveModelObject(myModelCollection);
    myVisualController.updateCommands(command);
    myVisualController.updateVariables(new BasicVariable("guy", 2));
     */
    myVisualController.updateErrors(new InvalidCommandException("Testing Error (thrown from "
            + "Logical Controller)"));
  }

  private void printTurtleState(ModelTurtle turtle, String seq) {
    System.out.println(seq + " Y " + turtle.getY());
    System.out.println(seq + " X " + turtle.getX());
  }

}