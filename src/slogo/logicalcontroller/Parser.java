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
    private ResourceBundle myLanguageResources;
    private static Map<String, String> myCommandSuperclassMap;
    private List<Command> finalCommandObjects;
    private List<String> rawCommands;
    private ModelCollection model;
    private List<Variable> variables;
    private List<String> command_input;
    private static final String SLOGO_COMMAND = "slogo.logicalcontroller.command.";
    private FileInputStream fis1 = new FileInputStream("src/properties/commandSuperclass.properties");
    private ResourceBundle myCommandMap = new PropertyResourceBundle(fis1);
    private Set<String> type2 = new HashSet<String>(Arrays.asList("random","sin","cos","tan","atan","log","pow","pi"));
    private Set<String> mathSingleParameter = new HashSet<String>(Arrays.asList("random","sin","cos","tan","atan","log","pi", "minus", "~"));
    private ArrayList<String> comNeedChecked = new ArrayList<String>(Arrays.asList("repeat","sin","cos","tan","atan","log","pow","pi"));
    private Set<String> mathDoubleParameter = new HashSet<String>(Arrays.asList("pow", "sum", "+", "difference", "-", "product", "*", "quotient", "/", "remainder", "%"));
    private Map<String, String> type1 = new HashMap<String, String>(){{
        put("sum", "+");
        put("difference", "-");
        put("product", "*");
        put("quotient", "/");
        put("remainder", "%");
        put("minus", "~");
    }};

    /**
     * Constructor for the Parser class that takes in the input language and initializes all the used variables that are required for parsing
     * @param language
     * @throws IOException
     */
    public Parser(String language) throws IOException {
        setLanguage(language);
        this.myCommandSuperclassMap = createCommandSuperclassMap();
    }

    public void setLanguage(String language) throws IOException {
        this.myLanguage = language;
        this.myLanguageResources = new PropertyResourceBundle(createFileStream());
    }

    private FileInputStream createFileStream() throws FileNotFoundException {
        return new FileInputStream("resources/languages/"+this.myLanguage + ".properties");
    }

    /**
     * Called by SlogoView with lines to parse into executable commmands
     * Two stage process, first
     * @param lines
     */
    public void parse(List<String> lines) throws NoSuchMethodException, InstantiationException, ScriptException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
        //try {
            this.finalCommandObjects = new ArrayList<Command>();
            this.rawCommands = lines;
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                String type = getType(line);
                line = checkForBoolean(line);
                i = checkForVCU(i);
                this.finalCommandObjects.addAll(singleLineParse(line));
            }
        //} catch (Exception e) {
        //    throw new InvalidCommandException();
        //}
    }

    // TODO - Fill in method stubs, using refactored parser process
    /**
     * Traverses array of raw commands, finds next non constant line
     * @return
     */
    private int findNextLine() {
        for(int i = 0; i< rawCommands.size(); i++){
            String s = rawCommands.get(i);
            if(s.split("\\s+").length >0){
                return i;
            }
        }
        return 0;
    }

    /**
     * Input single line of text, output index of last commandkeyword
     * @return
     */
    private int findLastCommand(String line) {
        String[] lineElems = line.split("\\s+");

        for(int i = lineElems.length-1; i>=0; i--){
            if(lineElems[i].matches(".*\\d.*")){
                continue;
            }
            else{
                return i;
            }
        }
        return -1;
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

    //TODO Change the catch statements to throw the right exceptions
    public void executeNextCommand(){
        try {
            singleLineParse(rawCommands.get(findNextLine()));
        } catch (ScriptException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }

    /**
     * Set of executable commands on specific objects
     * @param command
     * @return
     */
    private List<String> executeModifierCommand(ModifierCommand command) {
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

    private String getType(String line) {

        return null;
    }

    // TODO - simplify boolean from line
    private String checkForBoolean(String line) {

        return line;
    }

    private List<Command> singleLineParse(String linee) throws ScriptException, ConstructorException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        //try {
            String line = linee;
            Stack<String> commands = new Stack<String>();
            Stack<String> values = new Stack<String>();
            if (line.trim().length() > 0) {
                String[] splited = line.split("\\s+");
                String com = returnCommands(splited);
                double math = checkMath(splited);
                // double math = checkMath2(splited);
                System.out.println("math return: " + math);
                String new_splited_string = com;
                if(math!=0.0){ new_splited_string = com+" " + math; }
                String[] new_splited = new_splited_string.split(" ");
                for(String s: new_splited){
                    if(!checkHasNumber(s)){ commands.push(s); }
                    else{ values.push(s); }
                }
            }
            return unravel(commands, values);
        //} catch (Exception e) {
         //   throw new InvalidCommandException();
        //}
    }

    // TODO - update return value to have new index (if repeat taken)
    // TODO - accept repeat in any position, not just first position in line
    private int checkForVCU(int index) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, ScriptException {
        Set<String> vcuTypes = new HashSet<String>(Arrays.asList("repeat","dotimes","make","set","for","if","ifelse","to"));
        boolean doExit = false;
        int ret = 0;
        while(!doExit){
            String[] line = (rawCommands.get(index)).split("\\s+");
            for(int i = 0; i<line.length; i++){
                if(vcuTypes.contains(line[i])){
                    if(line[i].equals("repeat")){
                        index = parseRepeat(line,index);
                    }
                }
            }
            doExit = true;
        }
        return index;
    }

    // TODO - assume only one repeat, update to handle multiple
    private int parseRepeat(String[] repLine, int index) throws NoSuchMethodException, InstantiationException, ScriptException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
        String value = repLine[1];
        int currLine = index+1;
        int ret = index;
        List<String> tempRetLines = new ArrayList<String>();
        List<Command> repCommands = new ArrayList<Command>();
        List<Command> commandsToBeAdded = new ArrayList<Command>();

        while(!(rawCommands.get(currLine)).contains("]")){
            tempRetLines.add((rawCommands.get(currLine)));
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

    // TODO - implement math objects instead of Math engine
    @Deprecated
    private double checkMath2(String[] splitted) {
        try {
            String text;
            System.out.println("Checking math2");
            Stack<String> operator = new Stack<String>();
            Stack<Double> argument = new Stack<Double>();
            for (int i = splitted.length-1; i >= 0; i --) {
                text = splitted[i];
                // if(checkHasNumber(text)) {argument.push()};
            }
            for (int i = 0; i < splitted.length; i ++) {
                text = splitted[i];
                System.out.println("text: " + text);
                Class clazz = Class.forName("slogo.logicalcontroller.command.math." + myCommandMap.getString(text));
                Constructor constructor = clazz.getConstructor(String.class);
                MathCommand command = (MathCommand) constructor.newInstance(splitted[i+1]);
                command.performMath();
                return command.getValue();
            }
            return Double.parseDouble(splitted[splitted.length-1]);
        } catch (Exception e) {
            System.out.println("exception in math2");
            throw new ConstructorException();
        }
    }

    // TODO - refactor as MathCommands
    private double checkMath(String[] splited) throws ScriptException {
        String op = retMath(splited);
        String[] operation = op.split("\\s+");
        for(int i = 0; i < operation.length; i ++) {
            if ((type1.keySet()).contains(operation[i])) {
                operation[i] = operation[i + 1];
                operation[i + 1] = type1.get(operation[i]);
                i += 2;
            } else if (type2.contains(operation[i])) {
                operation[i] = "Math." + operation[i];
                operation[i + 1] = "(" + operation[i + 1] + ")";
                i += 1;
            }
        }

        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        String temp = String.join("", operation);

        if(String.valueOf(engine.eval(temp)) == null || (String.valueOf(engine.eval(temp)).equals("")) || (String.valueOf(engine.eval(temp)).equals("null"))){
            return 0.0;
        }
        double ret = Double.parseDouble(String.valueOf(engine.eval(temp)));
        return ret;
    }

    /**
     * Takes in string array of split input string, returns all non math commands
     * @param splited
     * @return
     */
    private String returnCommands(String[] splited){
        Set<String> mathTypes = new HashSet<String>(Arrays.asList("random","sin","cos","tan","atan","log","pow","pi","sum", "+","difference", "-","product","*","quotient","/","remainder", "%","minus","~"));
        StringBuilder sb = new StringBuilder();
        for(String s: splited){
            if(!mathTypes.contains(s) && !s.matches(".*\\d.*")){
                sb.append(s + " ");
            }
        }
        return sb.substring(0, sb.length()-1);
    }

    /**
     * Checks for non standard math operations so they can be set
     * @param splited
     * @return
     * TODO - use stringBuilder to cut down on line
     */
    private String retMath(String[] splited){
        Set<String> mathTypes = new HashSet<String>(Arrays.asList("random","sin","cos","tan","atan","log","pow","pi","sum", "+","difference", "-","product","*","quotient","/","remainder", "%","minus","~"));

        if(!(String.join(" ", splited)).matches(".*\\d.*")){
            return "";
        }
        String math = "";
        for(String s: splited){
            if(mathTypes.contains(s) || s.matches(".*\\d.*")){
                math+=s + " ";
            }
        }

        math = math.substring(0, math.length()-1);
        return math;
    }

    /**
     * Called by single line parse to create command list from expanded single line Strings by dual pointer stack
     * @return
     */
    private List<Command> unravel(Stack<String> commands, Stack<String> values) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        //try {
            List<Command> singleLineCommands = new ArrayList<Command>();
            String lastValue = "";
            System.out.println("Commands: ");
            System.out.println(values);
            while(!values.isEmpty()){
                lastValue = values.pop();
                System.out.println("LastVal" + lastValue);
                singleLineCommands.add(getConstructor(commands.pop(), lastValue));
                System.out.println("Comm" + singleLineCommands);
            }
            while(!commands.isEmpty() && values.isEmpty()){
                singleLineCommands.add(getConstructor(commands.pop(), lastValue));
            }
            return singleLineCommands;
        //} catch (ConstructorException e) {
         //   throw new ConstructorException();
        //}
    }

    /**
     * Called internally in unravel
     * Applies reflection on command string to create a command object with a given value
     * @param com
     * @param val
     * @returns Commmand object
     */
    private Command getConstructor(String com, String val) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
       // try {
            System.out.println("Vallll" + val);

            // Class cl = Class.forName(SLOGO_COMMAND+commandMappings.get(commandArray.get(com))+"."+commandArray.get(com));
            Class cl = Class.forName(SLOGO_COMMAND+myCommandMap.getString(myCommandSuperclassMap.get(com))+"."+ myCommandSuperclassMap.get(com));
            System.out.println("The val: " +  val);
            Constructor con = cl.getConstructor(String.class);
            Command command = (Command) con.newInstance(val);
            System.out.println("Da command" + command);
            return command;
        //} catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
         //   throw new ConstructorException();
       // }
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
     * Called internally, to create mapping of every input commmand to the command objects
     * Based on preconfigured language
     * @return mappings of input commmands to command objects (e.g lt --> Left)
     */
    private Map<String, String> createCommandSuperclassMap() {
        Map<String, String> mymap = new HashMap<String, String>();
        for(String key: Collections.list(this.myLanguageResources.getKeys())){
            String regex = this.myLanguageResources.getString(key);
            if(regex.indexOf("|") != -1){
                mymap.put(regex.substring(0, regex.indexOf("|")), key);
                mymap.put(regex.substring(regex.indexOf("|")+1), key);
            }
            else{
                mymap.put(regex, key);
            }
        }
        return mymap;
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

    private static String testTranslate(Parser p, String language, String command) {
        try {
            p.setLanguage(language);
            String key = p.translateCommand(command);
            System.out.printf("Translated %s to %s in %s \n", command, key, language);
            return key;
        } catch (Exception e) {
            System.out.println("Exception in testTranslate");
            throw new InvalidCommandException("Could not translate commmand");
        }
    }

    private static void testSuperclassMap(Parser p) {
        String command = "SetHeading";
        String clazz = p.getCommandSuperclass(command);
        System.out.printf("%s --> %s \n", command, clazz);
    }

    private static Command testCommandCreation(Parser p) {
        String superclass = "modifier";
        String command = "SetHeading";
        List<String> arguments = new ArrayList<String>(List.of("50", "42"));
        Command c = p.createCommand(superclass, command, arguments);
        System.out.printf("command.toString(): %s \n", c.toString());
        return c;
    }

    private static void testCommandCycle() throws IOException {
        List<String> userInput = new ArrayList<String>(List.of("vpered 50"));
        String language = "Russian";
        Parser p = new Parser(language);
        // TODO - take user input, return command and arguments
        String command = "vpered";
        List<String> arguments = new ArrayList<String>(List.of("50"));
        String translated = p.translateCommand(command);
        String superclass = p.getCommandSuperclass(translated);
        Command c = p.createCommand(superclass, translated, arguments);
        List<String> myList = p.executeCommand(c);
        for (String s: myList) {
            System.out.print(s);
        }
    }

    public static void main (String[] args) throws IOException, NoSuchMethodException, InstantiationException, ScriptException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
        //testCommandCycle();
    }
}
