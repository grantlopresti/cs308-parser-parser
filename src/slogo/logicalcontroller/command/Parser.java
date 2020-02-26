package slogo.logicalcontroller.command;

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
    private ArrayList<Command> commandObjs;
    private ResourceBundle resources;
    private HashMap<String, String> type1;
    private HashSet<String> type2;
    private ModelCollection model;
    private List<Variable> variables;
    private List<String> command_input;

    /**
     * Constructor for the Parser class that takes in the input language and initializes all the used variables that are required for parsing
     * @param language
     * @throws IOException
     */
    public Parser(String language) throws IOException {
        this.lang = language;
        commandArray = new HashMap<String, String>();
        commands = new Stack<String>();
        values = new Stack<String>();
        commandObjs = new ArrayList<Command>();
        FileInputStream fis = new FileInputStream("resources/languages/"+this.lang+".properties");
        resources = new PropertyResourceBundle(fis);
        genCommandArray();

    }

    /**
     * Returns the final list of commands for the model to execute
     * @return
     */
    public ArrayList<Command> getCommands(){
        return this.commandObjs;
    }

    public void parse(List<String> lines) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, ScriptException {
        for (String line : lines) {
            int numVals = 0;
            int numCommands = 0;
            int compoundVal = 0;
            if (line.trim().length() > 0) {
                String[] splited = line.split("\\s+");
                String com = retComs(splited);
                double math = checkMath(splited);
                String new_splited_string = com+" " + math;
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
            unravel(compoundVal);
        }

    }

    public void executeNextCommand(){

    }

    public double checkMath(String[] splited) throws ScriptException {
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
        System.out.println(String.join("", operation));
        double ret = (double)engine.eval(String.join("", operation));
        return ret;
    }

    public void set(List<String> command, ModelCollection modelC, List<Variable> var){
        this.command_input = command;
        this.model = modelC;
        this.variables = var;
    }

    public boolean isFinished(){
        return true;
    }

    public ModelCollection getModel(){
        return this.model;
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

    public String retMath(String[] splited){

        HashSet mathTypes = new HashSet<String>(Arrays.asList("random","sin","cos","tan","atan","log","pow","pi","sum", "+","difference", "-","product","*","quotient","/","remainder", "%","minus","~"));

        String math = "";
        for(String s: splited){
            if(mathTypes.contains(s) || s.matches(".*\\d.*")){
                math+=s + " ";
            }
        }

        math = math.substring(0, math.length()-1);
        return math;
    }

    public void unravel(int cv) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        String theVal = null;
        int comVal = cv;
        while(!commands.isEmpty() && !values.isEmpty()){
            Class cl = Class.forName("slogo.logicalcontroller.command."+commandArray.get(commands.pop()));
            Constructor con = cl.getConstructor(String.class);
            theVal = values.pop();
            Object obj = con.newInstance(theVal);
            commandObjs.add((Command) obj);
        }
        while(!commands.isEmpty() && comVal>0){
            Class cl = Class.forName("slogo.logicalcontroller.command."+commandArray.get(commands.pop()));
            Constructor con = cl.getConstructor(String.class);
            Object obj = con.newInstance(theVal);
            commandObjs.add((Command) obj);
            comVal--;
        }
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


    public String getSymbol(String text){
        final String ERROR = "NO MATCH";
        for(String s:commandArray.keySet()){
            if(isMatch(s, commandArray.get(s))){
                return commandArray.get(s);
            }
        }
        return ERROR;
    }

    public boolean isMatch(String text, String regex){
        return regex.matches(text);
    }

    private void genCommandArray() {
        for(String key: Collections.list(resources.getKeys())){
            String regex = resources.getString(key);
            if(regex.indexOf("|") != -1){
                commandArray.put(regex.substring(0, regex.indexOf("|")), key);
                commandArray.put(regex.substring(regex.indexOf("|")+1), key);
            }
            else{
                commandArray.put(regex, key);
            }
        }
    }

    public String getLang(){
        return this.lang;
    }

    public static void main (String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ScriptException {
        Parser p = new Parser("English");
        List<String> test = new ArrayList<String>();
        test.add("fd 50");
        p.parse(test);
        ArrayList<Command> testt = p.getCommands();
        for(Command c: testt){
            System.out.println(c);
        }

    }
}
