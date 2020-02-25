package slogo.logicalcontroller.command;

import java.util.*;
import java.io.*;
import java.lang.reflect.*;

public class Parser {

    private String lang;
    private Map<String, String> commandArray;
    private Stack<String> commands;
    private Stack<String> values;
    private ArrayList<Command> commandObjs;
    private ResourceBundle resources;

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

    public ArrayList<Command> getCommands(){
        return this.commandObjs;
    }

    public void parse(List<String> lines) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        for (String line : lines) {
            int numVals = 0;
            int numCommands = 0;
            int compoundVal = 0;
            if (line.trim().length() > 0) {
                String[] splited = line.split("\\s+");
                boolean prev = false;
                for(String s: splited){
                    if(!hasValue(s)){
                        commands.push(s);
                        prev = true;
                        numCommands++;
                    }
                    else{
                        values.push(s);
                        numVals++;
                    }
                }

            }

            if(numCommands>numVals){
                compoundVal = numCommands-numVals;
            }
            System.out.println(compoundVal);
            unravel(compoundVal);
        }

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
            System.out.println((Command)obj);
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

    public static void main (String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Parser p = new Parser("English");
        List<String> test = new ArrayList<String>();
        test.add("fd fd fd fd 50");
        p.parse(test);
        ArrayList<Command> testt = p.getCommands();
        for(Command c: testt){
            System.out.println(testt);
        }

    }
}
