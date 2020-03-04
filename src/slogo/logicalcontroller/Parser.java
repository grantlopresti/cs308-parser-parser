package slogo.logicalcontroller;

import slogo.exceptions.InvalidCommandException;
import slogo.exceptions.NoCommandFound;
import slogo.exceptions.ReflectionException;
import slogo.exceptions.ResourceBundleCreationException;
import slogo.logicalcontroller.command.Command;
import slogo.logicalcontroller.command.comparison.ComparisonCommand;
import slogo.logicalcontroller.command.controlflow.ControlFlowCommand;
import slogo.logicalcontroller.command.math.MathCommand;
import slogo.logicalcontroller.command.modifier.ModifierCommand;
import slogo.logicalcontroller.command.querie.QuerieCommand;
import slogo.logicalcontroller.input.UserInput;
import slogo.logicalcontroller.variable.Variable;
import slogo.model.ModelCollection;

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
    private List<Variable> myVariableList;
    private UserInput myUserInput;
    private ModelCollection myModelCollection;
    private ResourceBundle myLanguageResources;
    private boolean myFinished;

    /**
     * Constructor for the Parser class that takes in the input language and initializes all the used variables that are required for parsing.
     * @param language
     */
    public Parser(String language) throws IOException {
        setLanguage(language);
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
        this.finalCommandObjects = new ArrayList<Command>();
        this.myUserInput = new UserInput(lines, this.myLanguageResources);
        for (int i = 0; i < lines.size(); i++) {
            this.finalCommandObjects.addAll(singleLineParse(lines.get(i)));
        }
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
    // TODO - fill in method body
    public void executeNextCommand(){
        ;
    }

    /**
     * Set of executable commands on specific objects
     * @param command
     * @return
     */
    private List<String> executeModifierCommand(ModifierCommand command) {
        System.out.printf("Executing command %s with argument %.2f\n", command.getClass().getSimpleName(), command.getArgument1());
        return new ArrayList<String>(List.of(command.execute()));
    }

    private List<String> executeComparisonCommand(ComparisonCommand command) {
        return new ArrayList<String>();
    }

    private List<String> executeControlFlowCommand(ControlFlowCommand command) {
        return new ArrayList<String>();
    }

    private List<String> executeMathCommand(MathCommand command) {
        return new ArrayList<String>();
    }

    private List<String> executeQuerieCommand(QuerieCommand command) {
        return new ArrayList<String>();
    }

    private List<Command> singleLineParse(String linee) {
        return new ArrayList<Command>();
    }

    /**
     * Called by the LogicalController
     * Returns the final list of commands to be executed on the model
     * @return
     */
    public List<Command> getCommands(){
        return this.finalCommandObjects;
    }

    public Command getLatestCommand() {
        return this.myUserInput.getNextCommand();
    }

    public List<Variable> getVariables() {return this.myVariableList; }

    public boolean isFinished(){
        return true;
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

    // TODO - incorporate command cycle into parser for real time processing
    private static void testCommandCycle() throws IOException {
        try {
            String language = "Russian";
            Parser p = new Parser(language);
            List<String> userInput = new ArrayList<String>(List.of("40", "60", "75", "vpered vpered 50"));
            UserInput myInput = new UserInput(userInput, p.getLanguageResources());
            // while (myInput.hasNext()) {
            Command c = myInput.getNextCommand();
            List<String> myList = p.executeCommand(c);
            for (String s: myList) {
                System.out.print(s);
            }
            myInput.setCodeReplacement(myList);
            // it}
        } catch (NoCommandFound e) {
            System.out.println("Parser finished parsing lines");
        }

    }

    public static void main (String[] args) throws IOException {
        testCommandCycle();
    }
}
