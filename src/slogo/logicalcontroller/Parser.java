package slogo.logicalcontroller;

import slogo.exceptions.ConstructorException;
import slogo.exceptions.InvalidCommandException;
import slogo.logicalcontroller.command.Command;
import slogo.logicalcontroller.command.comparison.ComparisonCommand;
import slogo.logicalcontroller.command.controlflow.ControlFlowCommand;
import slogo.logicalcontroller.command.controlflow.Repeat;
import slogo.logicalcontroller.command.math.MathCommand;
import slogo.logicalcontroller.command.modifier.ModifierCommand;
import slogo.logicalcontroller.command.querie.QuerieCommand;
import slogo.logicalcontroller.input.UserInput;
import slogo.logicalcontroller.variable.Variable;
import slogo.model.ModelCollection;

import java.util.*;
import java.io.*;
import java.lang.reflect.*;
import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

/**
 * Purpose of this class is to parse incoming commands from the console and from a text file that the user will have an option to read in.
 */
public class Parser {

    private String myLanguage;
    private List<Command> finalCommandObjects;
    private UserInput myUserInput;
    private ModelCollection model;
    private List<Variable> variables;
    private List<String> command_input;
    private static final String SLOGO_COMMAND = "slogo.logicalcontroller.command.";
    private static final String SUPERCLASS_PROPERTIES = "src/properties/commandSuperclass.properties";
    private static final String PARAMETER_PROPERTIES = "src/properties/parameterCount.properties";
    private static ResourceBundle myCommandMap;
    private static ResourceBundle myParameterMap;
    private ResourceBundle myLanguageResources;

    /**
     * Constructor for the Parser class that takes in the input language and initializes all the used variables that are required for parsing
     * @param language
     * @throws IOException
     */
    public Parser(String language) throws IOException {
        setLanguage(language);
        this.myCommandMap = createResourceBundle(SUPERCLASS_PROPERTIES);
        this.myParameterMap = createResourceBundle(PARAMETER_PROPERTIES);
    }

    public void setLanguage(String language) throws IOException {
        this.myLanguage = language;
        this.myLanguageResources = createResourceBundle(nameLanguageFile());
    }

    private String nameLanguageFile() {
        return "resources/languages/" + this.myLanguage + ".properties";
    }

    private ResourceBundle createResourceBundle(String filename) throws IOException {
        return new PropertyResourceBundle(new FileInputStream(filename));
    }

    /**
     * Called by SlogoView with lines to parse into executable commmands
     * Two stage process, first
     * @param lines
     */
    public void parse(List<String> lines) {
        try {
            this.finalCommandObjects = new ArrayList<Command>();
            this.myUserInput = new UserInput(lines, this.myLanguageResources);
            for (int i = 0; i < lines.size(); i++) {
                this.finalCommandObjects.addAll(singleLineParse(lines.get(i)));
            }
        } catch (Exception e) {
            throw new InvalidCommandException();
        }
    }

    /**
     * Translates raw user inputted command (in arbitrary language) to Key in properties file
     * @param command
     * @return
     * TODO - what to do if command not found? - throw no command exception
     */
    private String translateCommand(String command) {
        Enumeration<String> resourceEnumeration = this.myLanguageResources.getKeys();
        String key; String value;
        while (resourceEnumeration.hasMoreElements()) {
            key = resourceEnumeration.nextElement();
            value = this.myLanguageResources.getString(key);
            if (value.contains(command)) {return key;}
        }
        return "";
    }

    /**
     * Use properties file to translate command Key into superclass for reflections
     * @param command
     * @return
     */
    private String getCommandSuperclass(String command) {
        Enumeration<String> resourceEnumeration = this.myCommandMap.getKeys();
        String key;
        while (resourceEnumeration.hasMoreElements()) {
            key = resourceEnumeration.nextElement();
            if (key.equals(command)) {return this.myCommandMap.getString(key);}
        }
        return "";
    }

    /**
     * Construct command using reflection based on given arguments
     * @param superclass
     * @param command
     * @param arguments
     * @return
     */
    private Command createCommand(String superclass, String command, List<String> arguments) {
        try {
            Class clazz = Class.forName(createCommandPath(superclass, command));
            Constructor ctor = clazz.getConstructor(List.class);
            return (Command) ctor.newInstance(arguments);
        } catch (Exception e) {
            throw new InvalidCommandException("Could not create command");
        }
    }

    private String createCommandPath(String superclass, String command) {
        String path = String.format("%s%s.%s", SLOGO_COMMAND, superclass, command);
        System.out.printf("returning path: %s \n", path);
        return path;
    }

    /**
     *
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
        } catch (Exception e) {
            throw new InvalidCommandException("Could not execute command");
        }
    }

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
        return new ArrayList<String>(List.of("Hello, I just executed a modifier command :)\n", "I hope this worked\n", "Slogo is fun\n"));
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

    // TODO - assume only one repeat, update to handle multiple
    private int parseRepeat(String[] repLine, int index) throws NoSuchMethodException, InstantiationException, ScriptException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
        String value = repLine[1];
        int currLine = index+1;
        int ret = index;
        List<String> tempRetLines = new ArrayList<String>();
        List<Command> repCommands = new ArrayList<Command>();
        List<Command> commandsToBeAdded = new ArrayList<Command>();

        while(!(this.myUserInput.getLine(currLine)).contains("]")){
            tempRetLines.add((this.myUserInput.getLine(currLine)));
            currLine++;
        }
        ret = currLine;

        for(String line: tempRetLines){
            repCommands.addAll(singleLineParse(line));
        }

        Repeat tempRepeat = new Repeat(value, repCommands);

        this.finalCommandObjects.addAll(tempRepeat.getAllRepCommands());
        return ret;
    }

    /**
     * Checks if a given string contains a digit value, or if it only has commands
     * @param line is a string line from the input
     * @returns true when the line contains a parseable integer
     */
    private boolean checkHasNumber(String line){
        try {
            double d = Double.parseDouble(line);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Called by the LogicalController
     * Returns the final list of commands to be executed on the model
     * @return
     */
    public List<Command> getCommands(){
        return this.finalCommandObjects;
    }

    public boolean isFinished(){
        return true;
    }

    public ModelCollection getModel(){
        return this.model;
    }

    public void set(List<String> command, ModelCollection modelC, List<Variable> var){
        this.command_input = command;
        this.model = modelC;
        this.variables = var;
    }

    private void setUserInput(List<String> userInput) {
        this.myUserInput = new UserInput(userInput, this.myLanguageResources);
    }

    private UserInput getUserInput() {
        return this.myUserInput;
    }

    private int countParameters(String translated) {
        return Integer.parseInt(this.myParameterMap.getString(translated));
    }

    private static void testCommandCycle() throws IOException {
        String language = "Russian";
        Parser p = new Parser(language);
        List<String> userInput = new ArrayList<String>(List.of("40", "60", "75", "vpered vpered 50"));
        p.setUserInput(userInput);
        int lineIndex = p.getUserInput().findNextLine();
        int commandIndex = p.getUserInput().findLastCommand(lineIndex);
        System.out.printf("found next command @line %d \n", lineIndex);
        System.out.printf("found last command @index %d \n", commandIndex);
        String command = "vpered";
        String translated = p.translateCommand(command);
        System.out.printf("translated %s to %s in %s", command, translated, language);
        int params = p.countParameters(translated);
        System.out.printf("requires %d parameters", params);
        List<String> arguments = p.getUserInput().getArguments(lineIndex, commandIndex, params);
        String superclass = p.getCommandSuperclass(translated);
        Command c = p.createCommand(superclass, translated, arguments);
        List<String> myList = p.executeCommand(c);
        for (String s: myList) {
            System.out.print(s);
        }
    }

    public static void main (String[] args) throws IOException {
        testCommandCycle();
    }
}
