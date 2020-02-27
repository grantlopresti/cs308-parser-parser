package slogo.logicalcontroller;

import slogo.logicalcontroller.command.Command;
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
    private Stack<String> commands;
    private Stack<String> values;
    private List<Command> finalCommandObjects;
    private ResourceBundle resources;
    private HashMap<String, String> type1;
    private HashSet<String> type2;
    private ModelCollection model;
    private List<Variable> variables;
    private List<String> command_input;
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
    private List<String> rawCommands;

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
     * @param lines
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ScriptException
     */
    public void parse(List<String> lines) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, ScriptException {
        this.rawCommands = lines;
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            line = checkForBoolean(line);
            i = checkForVCU(i);
            finalCommandObjects.addAll(singleLineParse(line));
        }
    }

    // TODO - simplify boolean from line
    private String checkForBoolean(String line) {

        return line;
    }

    private List<Command> singleLineParse(String linee) throws ScriptException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        String line = linee;
        commands = new Stack<String>();
        values = new Stack<String>();
        int numVals = 0;
        int numCommands = 0;
        int compoundVal = 0;
        if (line.trim().length() > 0) {
            String[] splited = line.split("\\s+");
            String com = retComs(splited);
            double math = checkMath(splited);
            String new_splited_string = com;
            if(math!=0.0){
                new_splited_string = com+" " + math;
            }
            String[] new_splited = new_splited_string.split(" ");
            boolean prev = false;
            for(String s: new_splited){
                if(!hasValue(s)){
                    commands.push(s);
                    prev = true;
                    numCommands++;
                } else{
                    values.push(s);
                    numVals++;
                }
            }
        }

        if(numCommands>numVals){
            compoundVal = numCommands-numVals;
        }
        return unravel(compoundVal);
    }

    public void executeNextCommand(){

    }

    // TODO - update return value to have new index (if repeat taken)
    private int checkForVCU(int index) throws ClassNotFoundException, NoSuchMethodException {
        HashSet vcuTypes = new HashSet<String>(Arrays.asList("repeat","dotimes","make","set","for","if","ifelse","to"));
        ArrayList<Command> tempret = new ArrayList<Command>();
        int startInd = 0;
        int endInd = 0;
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
                    Constructor con = cl.getConstructor(String.class);

                    //Object obj = con.newInstance(theVal);
                    //commandObjs.add((Command) obj);
                }
            }
            doExit = true;
        }
        return index;
    }

    public void parseRepeat(String[] repLine, int index){
        ArrayList<Command> repCommands = new ArrayList<Command>();
        String[] rpLine = repLine;
        int frontBracks = 1;
        int endBracks = 0;
        int endIndex;
        while(frontBracks!=endBracks){
            int i;
            for(i = index; i<rawCommands.size(); i++){
                if(rawCommands.get(i).indexOf('[')!=-1){
                    frontBracks++;
                }
                else if(rawCommands.get(i).indexOf(']')!=-1){
                    endBracks++;
                }
            }
            if(frontBracks==endBracks){
                endIndex = i;
                break;
            }
            else{

            }
        }
        //Repeat repeat = new Repeat(repLine[1]);
    }

    private double checkMath(String[] splited) throws ScriptException {
        String op = retMath(splited);
        String[] operation = op.split("\\s+");
        type1 = new HashMap<String, String>(){{
            put("sum", "+");
            put("difference", "-");
            put("product", "*");
            put("quotient", "/");
            put("remainder", "%");
            put("minus", "~");
        }};
        type2 = new HashSet<String>(Arrays.asList("random","sin","cos","tan","atan","log","pow","pi"));
        for(int i = 0; i<operation.length; i++){
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
    }

    public void set(List<String> command, ModelCollection modelC, List<Variable> var){
        command_input = command;
        model = modelC;
        variables = var;
    }

    public boolean isFinished(){
        return true;
    }

    public ModelCollection getModel(){
        return model;
    }

    public Command getCommand(){
        return null;
    }

    public String retComs(String[] splited){
        HashSet mathTypes = new HashSet<String>(Arrays.asList("random","sin","cos","tan","atan","log","pow","pi","sum", "+","difference", "-","product","*","quotient","/","remainder", "%","minus","~"));

        String com = "";
        for(String s: splited){
            if(!mathTypes.contains(s) && !s.matches(".*\\d.*")){
                com+=s + " ";
            }
        }

        com = com.substring(0, com.length()-1);
        return com;
    }

    private String retMath(String[] splited){

        HashSet mathTypes = new HashSet<String>(Arrays.asList("random","sin","cos","tan","atan","log","pow","pi","sum", "+","difference", "-","product","*","quotient","/","remainder", "%","minus","~"));

        String temp = String.join(" ", splited);
        System.out.println();
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

    private List<Command> unravel(int commandsLeft) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        List<Command> singleLineCommands = new ArrayList<Command>();
        String lastValue = "";
        while(!values.isEmpty()){
            lastValue = values.pop();
            singleLineCommands.add(getConstructor(commands.pop(), lastValue));
        }
        while(!commands.isEmpty() && commandsLeft > 0){
            singleLineCommands.add(getConstructor(commands.pop(), lastValue));
            commandsLeft--;
        }
        return singleLineCommands;
    }

    private Command getConstructor(String com, String val) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        System.out.println(com);
        Class cl = Class.forName("slogo.logicalcontroller.command."+commandMappings.get(commandArray.get(com))+"."+commandArray.get(com));
        Constructor con = cl.getConstructor(String.class);
        Command command = (Command) con.newInstance(val);
        return command;
    }

    private boolean hasValue(String val){
        char[] chars = val.toCharArray();
        for(char c: chars){
            if(Character.isDigit(c)){
                return true;
            }
        }
        return false;
    }

    private String getSymbol(String text){
        final String ERROR = "NO MATCH";
        for(String s:commandArray.keySet()){
            if(isMatch(s, commandArray.get(s))){
                return commandArray.get(s);
            }
        }
        return ERROR;
    }

    private boolean isMatch(String text, String regex){
        return regex.matches(text);
    }

    private Map<String, String> genCommandArray() {
        Map<String, String> mymap = new HashMap<String, String>();
        for(String key: Collections.list(resources.getKeys())){
            String regex = resources.getString(key);
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
     * Returns the final list of commands for the model to execute
     * @return
     */
    public List<Command> getCommands(){
        return this.finalCommandObjects;
    }

    public String getLang(){
        return this.lang;
    }

    public static void main (String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ScriptException {
        Parser p = new Parser("English");
        List<String> test = new ArrayList<String>(List.of("fd fd fd fd 50+50"));
        p.parse(test);
        List<Command> testt = p.getCommands();
        System.out.println(testt);
    }
}
