package slogo.logicalcontroller;

import slogo.exceptions.InvalidCommandException;
import slogo.exceptions.NoCommandFound;
import slogo.exceptions.ReflectionException;
import slogo.exceptions.ResourceBundleCreationException;
import slogo.logicalcontroller.command.Command;
import slogo.logicalcontroller.command.MakeVariable;
import slogo.logicalcontroller.command.comparison.ComparisonCommand;
import slogo.logicalcontroller.command.controlflow.ControlFlowCommand;
import slogo.logicalcontroller.command.math.MathCommand;
import slogo.logicalcontroller.command.modifier.ModifierCommand;
import slogo.logicalcontroller.command.querie.QuerieCommand;
import slogo.logicalcontroller.input.UserInput;
import slogo.logicalcontroller.variable.Variable;
import slogo.logicalcontroller.variable.VariableList;
import slogo.model.ModelCollection;
import slogo.model.ModelTurtle;

import java.util.*;
import java.io.*;
import java.lang.reflect.*;

/**
 * Purpose of this class is to parse incoming commands from the console and from a text file that the user will have an option to read in.
 * @author Amjad S
 */
public class Parser implements BundleInterface {

    private String myLanguage;
    private List<Command> finalCommandObjects;
    private VariableList myVariableList;
    private UserInput myUserInput;
    private ModelCollection myModelCollection;
    private ResourceBundle myLanguageResources;
    private Command myLatestCommand;
    private boolean myFinished;

    /**
     * Constructor for the Parser class that takes in the input language and initializes all the used variables that are required for parsing.
     * @param language
     */
    public Parser(String language, ModelCollection model) throws IOException {
        setLanguage(language);
        this.myModelCollection = model;
    }

    /**
     * Reads in the language of the appropriate resource file and loads it into a resource bundle for future use.
     * @param language
     */
    public void setLanguage(String language) throws IOException {
        this.myLanguage = language;
        try {
            this.myLanguageResources = BundleInterface.createResourceBundle(nameLanguageFile());
        } catch (IOException e) {
            throw new ResourceBundleCreationException();
        }
    }

    /**
     * Method to find out name of the resource file, based on whatever language the user selected
     * @return String representing the file path of the resource file
     */

    private String nameLanguageFile() {
        return "resources/languages/" + this.myLanguage + ".properties";
    }

    /**
     * Called by SlogoView with lines to parse into executable commmands
     * Two stage process, first
     * @param lines
     */
    public void parse(List<String> lines) {
        this.myUserInput = new UserInput(lines, this.myLanguageResources);
    }

    /**
     * @param command use reflection on command superclass to route command to appropriate helper method
     * @return list of strings to replace that command in the UserInput
     */
    private List<String> executeCommand(Command command) {
        try {
            Class superclazz = command.getClass().getSuperclass();
            String name = "execute" + superclazz.getSimpleName();
            Method method = this.getClass().getDeclaredMethod(name, superclazz); //Command.class
            Object o = method.invoke(this, command);
            return (List<String>) o;
        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException | NullPointerException e) {
            throw new ReflectionException("Unable to apply Reflection");
        }
    }

    /**
     * Method to find and execute the next command in the arraylist of raw commands. Represents one step of the turtle.
     */
    public void executeNextCommand(){
        this.myLatestCommand = this.myUserInput.getNextCommand();
        List<String> myList = this.executeCommand(this.myLatestCommand);
        this.myUserInput.setCodeReplacement(myList, this.myLatestCommand);
    }

    // TODO - execute on a specific turtle
    private List<String> executeModifierCommand(ModifierCommand command) {
        String replace = "";
        for (Object o : this.myModelCollection){
            ModelTurtle turtle = (ModelTurtle) o;
            // replace = command.execute(turtle);
        }
        return new ArrayList<String>(List.of(replace));
    }

    // TODO - execute on a specific turtle
    private List<String> executeQuerieCommand(QuerieCommand command) {
        for (Object o : this.myModelCollection){
            ModelTurtle turtle = (ModelTurtle) o;
            command.execute(turtle);
        }
        return new ArrayList<String>();
        // return new ArrayList<String>(List.of(command.codeReplace()));
    }

    private List<String> executeComparisonCommand(ComparisonCommand command) {
        return new ArrayList<String>(List.of(command.execute()));
    }

    private List<String> executeControlFlowCommand(ControlFlowCommand command) {
        return new ArrayList<String>();
    }

    private List<String> executeMathCommand(MathCommand command) {
        return new ArrayList<String>(List.of(command.execute()));
    }

    private List<String> executeVariables(MakeVariable command) {return new ArrayList<String>();}

    /**
     * Called by the LogicalController
     * Returns the final list of commands to be executed on the model
     * @return
     */
    public List<Command> getCommands(){
        return this.finalCommandObjects;
    }

    public Command getLatestCommand() {
        return this.myLatestCommand;
    }

    public VariableList getVariables() {return this.myVariableList; }

    public boolean isFinished(){
        return this.myUserInput.isFinished();
    }

    public ModelCollection getModel(){
        return this.myModelCollection;
    }

    private void setUserInput(List<String> userInput) {
        this.myUserInput = new UserInput(userInput, this.myLanguageResources);
    }

    private UserInput getUserInput() {
        return this.myUserInput;
    }

    public ResourceBundle getLanguageResources() {
        return this.myLanguageResources;
    }

}
