package slogo.logicalcontroller;

import slogo.exceptions.DeprecationException;
import slogo.exceptions.InvalidCommandException;
import slogo.exceptions.InvalidLanguageException;
import slogo.exceptions.LogicalException;
import slogo.logicalcontroller.command.Command;
import slogo.logicalcontroller.variable.VariableList;
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
 * @author Alex Xu, Max Smith
 */
public class LogicalController {
  private static final String DEFAULT_LANGUAGE = "ENGLISH";

  private Parser myParser;
  private ModelCollection myModelCollection;
  private VisualController myVisualController;
  private VariableList myVariables;

  public LogicalController(ModelCollection modelCollection, VisualController visualController, VariableList variables){
    this.myModelCollection = modelCollection;
    this.myVisualController = visualController;
    this.myVariables = variables;
    createParser();
  }

  private void createParser() {
    try {
      myParser= new Parser(DEFAULT_LANGUAGE, this.myModelCollection, this.myVariables);
    } catch (Exception e) {
      System.exit(0);
    }
  }

  /**
   * To be called from the front-end to change the language (also needs to happen the first time).
   * @param language
   */
  public void setLanguage(String language) {
    try {
      myParser.setLanguage(language);
    } catch (IOException e) {
      this.myVisualController.updateErrors(new InvalidLanguageException());
    }
  }

  /**
   * Code that interacts with the GUI, and receives strings as commands
   * Assumption is that there is only one ModelObject (Turtle)
   * @param fullUserInput
   * @throws InvalidCommandException
   */
  public void handleNewCommand(String fullUserInput) {
    try {
      this.myParser.parse(trimList(Arrays.asList(fullUserInput.split("\n"))));
      while(!this.myParser.isFinished()){
        this.myParser.executeNextCommand();
        Command latestCommand = this.myParser.getLatestCommand();
        ModelCollection newModel = this.myParser.getModel();
        VariableList newVariables = this.myParser.getVariables();
        this.myVisualController.update(newModel, newVariables, latestCommand);
      }
      this.myVisualController.updateCommand(fullUserInput);
    } catch (LogicalException e) {
      this.myVisualController.updateErrors(e);
    } catch (DeprecationException e) {
      this.myVisualController.deprecateProgram(e);
    }
  }

  // TODO - implement preprocessing to remove all comments from code
  private List<String> trimList(List<String> userInput) {
    return userInput;
  }
}