package slogo.logicalcontroller;

import slogo.exceptions.InvalidCommandException;
import slogo.logicalcontroller.command.Command;
import slogo.logicalcontroller.variable.VariableList;
import slogo.model.ModelCollection;
import slogo.visualcontroller.VisualController;

import javax.script.ScriptException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

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
  private VariableList myVariables;

  private LogicalController(){
  };

  public LogicalController(ModelCollection modelCollection, VisualController visualController, VariableList variables){
    this.myModelCollection = modelCollection;
    this.myVisualController = visualController;
    this.myVariables = variables;
    // TODO - update visualController initial state to empty lists to get first turtle to show
    // this.myVisualController.update(this.myModelCollection, this.myVariables, null);
    try {
      myParser= new Parser(DEFAULT_LANGUAGE, modelCollection);
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
    myParser.setLanguage(language);
  }

  /**
   * Code that interacts with the GUI, and receives strings as commands
   * Assumption is that there is only one ModelObject (Turtle)
   * @param fullUserInput
   * @throws InvalidCommandException
   */
  public void handleNewCommand(String fullUserInput) throws InvalidCommandException, NoSuchMethodException, InstantiationException, ScriptException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
    try {
      System.out.printf("recieved user input: %s \n", fullUserInput);             //TODO: Comment filtering should happen here
      this.myParser.parse(Arrays.asList(fullUserInput.split("\n")));
      while(!this.myParser.isFinished()){
        this.myParser.executeNextCommand();
        Command latestCommand = this.myParser.getLatestCommand();
        ModelCollection newModel = this.myParser.getModel();
        VariableList newVariables = this.myParser.getVariables();
        this.myVisualController.update(newModel, newVariables, latestCommand);
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("error in LogicalController.handleNewCommand");
    }
    System.out.println("Parser is finished! Yay!");
  }

  //TODO: Below is just for testing purposes, for the Parser functionality.
  public static void main (String[] args) throws IOException, NoSuchMethodException, InstantiationException, ScriptException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
    LogicalController test = new LogicalController();
    test.setLanguage(DEFAULT_LANGUAGE);

    String userInput = "fd fd 50 \n left 10";
    test.handleNewCommand(userInput);
  }

}