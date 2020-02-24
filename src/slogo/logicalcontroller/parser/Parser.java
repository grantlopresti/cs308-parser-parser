package slogo.logicalcontroller.parser;

import java.util.*;
import java.io.*;

public class Parser {

    private String lang;
    private Map<String, String> commandArray;
    ResourceBundle resources;

    public Parser(String language) throws IOException {
        this.lang = language;
        commandArray = new HashMap<String, String>();
        FileInputStream fis = new FileInputStream("resources/languages/"+this.lang+".properties");
        resources = new PropertyResourceBundle(fis);
        genCommandArray();
        System.out.println(Arrays.asList(this.commandArray));


    }

    public void parse(List<String> lines){
        for (String line : lines) {
            if (line.trim().length() > 0) {
                System.out.println(String.format("%s : %s", getSymbol(line)));
            }
        }
        System.out.println();
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

    public static void main (String[] args){
        System.out.println("Hello");
    }
}
