package slogo.logicalcontroller;

import slogo.exceptions.ConstructorException;
import slogo.exceptions.InvalidCommandException;
import slogo.logicalcontroller.command.Command;
import slogo.logicalcontroller.command.controlflow.Repeat;
import slogo.logicalcontroller.command.math.MathCommand;
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

    private String lang;
    private Map<String, String> commandArray;
    private List<Command> finalCommandObjects;
    private ResourceBundle resources;
    private List<String> rawCommands;

    // TODO - remove these (may not be needed with current structure)
    private ModelCollection model;
    private List<Variable> variables;
    private List<String> command_input;

    // TODO - refactor large constructed instance variables as enumerated types
    // ^^ DO THIS PLEASE !!!
    private Map<String, String> type1 = new HashMap<String, String>(){{
        put("sum", "+");
        put("difference", "-");
        put("product", "*");
        put("quotient", "/");
        put("remainder", "%");
        put("minus", "~");
    }};
    private Set<String> type2 = new HashSet<String>(Arrays.asList("random","sin","cos","tan","atan","log","pow","pi"));
    private Set<String> mathSingleParameter = new HashSet<String>(Arrays.asList(
            "random","sin","cos","tan","atan","log","pi", "minus", "~"));
    private Set<String> mathDoubleParameter = new HashSet<String>(Arrays.asList(
            "pow", "sum", "+", "difference", "-", "product", "*", "quotient", "/", "remainder", "%"));
    private Map<String, String> commandMappings = new HashMap<String, String>(){{
        put("And", "comparison");
        put("Equal", "comparison");
        put("GreaterThan", "comparison");
        put("LessThan", "comparison");
        put("Not", "comparison");
        put("NotEqual", "comparison");
        put("Or", "comparison");
        put("DoTimes", "controlflow");
        put("For", "controlflow");
        put("If", "controlflow");
        put("IfElse", "controlflow");
        put("Repeat", "controlflow");
        put("Backward", "modifier");
        put("Forward", "modifier");
        put("Left", "modifier");
        put("PenDown", "modifier");
        put("PenUp", "modifier");
        put("Right", "modifier");
        put("SetHeading", "modifier");
        put("SetPosition", "modifier");
        put("SetTowards", "modifier");
        put("ShowTurtle", "modifier");
        put("IsPenDown", "querie");
        put("IsShowing", "querie");
        put("XCoordinate", "querie");
        put("YCoordinate", "querie");
    }};

    /**
     * Constructor for the Parser class that takes in the input language and initializes all the used variables that are required for parsing
     * @param language
     * @throws IOException
     */
    public Parser(String language) throws IOException {
        this.lang = language;
        FileInputStream fis = new FileInputStream("resources/languages/"+this.lang+".properties");
        this.resources = new PropertyResourceBundle(fis);
        this.commandArray = genCommandArray();
        this.finalCommandObjects = new ArrayList<Command>();
        // System.out.println(commandArray);
    }

    /**
     * Called by SlogoView with lines to parse into executable commmands
     * Two stage process, first
     * @param lines
     */
    public void parse(List<String> lines) {
        try {
            this.finalCommandObjects = new ArrayList<Command>();
            this.rawCommands = lines;
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                line = checkForBoolean(line);
                i = checkForVCU(i);
                this.finalCommandObjects.addAll(singleLineParse(line));
            }
        } catch (Exception e) {
            throw new InvalidCommandException();
        }
    }

    // TODO - simplify boolean from line
    private String checkForBoolean(String line) {

        return line;
    }

    private List<Command> singleLineParse(String linee) throws ScriptException, ConstructorException {
        try {
            String line = linee;
            Stack<String> commands = new Stack<String>();
            Stack<String> values = new Stack<String>();
            if (line.trim().length() > 0) {
                String[] splited = line.split("\\s+");
                String com = returnCommands(splited);
                double math = checkMath(splited);
                String new_splited_string = com;
                if(math!=0.0){ new_splited_string = com+" " + math; }
                String[] new_splited = new_splited_string.split(" ");
                for(String s: new_splited){
                    if(!checkHasValue(s)){ commands.push(s); }
                    else{ values.push(s); }
                }
            }
            return unravel(commands, values);
        } catch (Exception e) {
            throw new InvalidCommandException();
        }
    }

    // TODO - update return value to have new index (if repeat taken)
    // TODO - accept repeat in any position, not just first position in line
    private int checkForVCU(int index) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, ScriptException {
        Set<String> vcuTypes = new HashSet<String>(Arrays.asList("repeat","dotimes","make","set","for","if","ifelse","to"));
        boolean doExit = false;
        while(!doExit){
            String[] line = (rawCommands.get(index)).split("\\s+");
            for(int i = 0; i<line.length; i++){
                if(vcuTypes.contains(line[i])){
                    if(line[i].equals("repeat")){
                        parseRepeat(line,index);
                    }
                    String temp = commandMappings.get(commandArray.get(line[i]));
                    Class cl = Class.forName("slogo.logicalcontroller.command."+temp+"."+commandArray.get(line[i]));
                    Constructor con = cl.getConstructor(String.class, List.class);

                    //Object obj = con.newInstance(theVal);
                    //commandObjs.add((Command) obj);
                }
            }
            doExit = true;
        }
        return index;
    }

    // TODO - assume only one repeat, update to handle multiple
    private void parseRepeat(String[] repLine, int index) throws NoSuchMethodException, InstantiationException, ScriptException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
        String value = repLine[1];
        int currLine = index+1;
        List<String> tempRetLines = new ArrayList<String>();
        List<Command> repCommands = new ArrayList<Command>();
        List<Command> commandsToBeAdded = new ArrayList<Command>();

        while(!(rawCommands.get(currLine)).contains("]")){
            tempRetLines.add((rawCommands.get(currLine)));
            currLine++;
        }

        for(String line: tempRetLines){
            repCommands.addAll(singleLineParse(line));
        }

        Repeat tempRepeat = new Repeat(value, repCommands);

        this.finalCommandObjects.addAll(tempRepeat.getAllRepCommands());
    }

    private double checkMath(String[] splitted) throws ScriptException {
        try {
            String text;
            for (int i = 0; i < splitted.length; i ++) {
                text = splitted[i];
                if (this.mathSingleParameter.contains(text)) {
                    Class clazz = Class.forName("slogo.logicalcontroller.command.math." + this.commandMappings.get(text));
                    Constructor constructor = clazz.getConstructor(String.class);
                    MathCommand command = (MathCommand) constructor.newInstance(splitted[i+1]);
                    return command.performMath();
                } else if (this.mathDoubleParameter.contains(text)) {
                    Class clazz = Class.forName("slogo.logicalcontroller.command.math." + this.commandMappings.get(text));
                    Constructor constructor = clazz.getConstructor(String.class);
                    MathCommand command = (MathCommand) constructor.newInstance(splitted[i+1], splitted[i+2]);
                    return command.performMath();
                }
            }
            return 0.0;
        } catch (Exception e) {
            throw new ConstructorException();
        }
    }

    // TODO - refactor as MathCommands
    /*
    private double checkMath(String[] splited) throws ScriptException {
        String op = retMath(splited);
        String[] operations = op.split("\\s+");
        for(String operation: operations){
            if((type1.keySet()).contains(operation[i])){
                String temp = operation[i];
                operation[i] = operation[i+1];
                operation[i+1] = type1.get(operation[i]);
                i+=2;
            }
            else if(type2.contains(operation[i])){
                operation[i] = "Math."+operation[i];
                operation[i+1] = "(" + operation[i+1] + ")";
                i+=1;
            }

        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        String temp = String.join("", operation);

        if(String.valueOf(engine.eval(temp)) == null || (String.valueOf(engine.eval(temp)).equals("")) || (String.valueOf(engine.eval(temp)).equals("null"))){
            return 0.0;
        }
        System.out.println("The condition");
        System.out.println(String.valueOf(engine.eval(temp)));
        double ret = Double.parseDouble(String.valueOf(engine.eval(temp)));
        return ret;
    } */

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
    private List<Command> unravel(Stack<String> commands, Stack<String> values) {
        try {
            List<Command> singleLineCommands = new ArrayList<Command>();
            String lastValue = "";
            while(!values.isEmpty()){
                lastValue = values.pop();
                singleLineCommands.add(getConstructor(commands.pop(), lastValue));
            }
            while(!commands.isEmpty()){
                singleLineCommands.add(getConstructor(commands.pop(), lastValue));
            }
            return singleLineCommands;
        } catch (ConstructorException e) {
            throw new ConstructorException();
        }
    }

    /**
     * Called internally in unravel
     * Applies reflection on command string to create a command object with a given value
     * @param com
     * @param val
     * @returns Commmand object
     */
    private Command getConstructor(String com, String val) {
        try {
            System.out.println(com);
            Class cl = Class.forName("slogo.logicalcontroller.command."+commandMappings.get(commandArray.get(com))+"."+commandArray.get(com));
            Constructor con = cl.getConstructor(String.class);
            Command command = (Command) con.newInstance(val);
            return command;
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            throw new ConstructorException();
        }
    }

    /**
     * Checks if a given string contains a digit value, or if it only has commands
     * @param line is a string line from the input
     * @returns true when the line contains a parseable integer
     */
    private boolean checkHasValue(String line){
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
    private Map<String, String> genCommandArray() {
        Map<String, String> mymap = new HashMap<String, String>();
        for(String key: Collections.list(this.resources.getKeys())){
            String regex = this.resources.getString(key);
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

    public String getLang(){
        return this.lang;
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

    public static void main (String[] args) {
        try {
            Parser p = new Parser("English");
            List<String> test = new ArrayList<String>();
            test.add("fd fd fd 50+50");
            p.parse(test);
            List<Command> testt = p.getCommands();
            System.out.println(testt);
        } catch (Exception e) {
            System.out.println("Exception in main");
        }

    }
}
