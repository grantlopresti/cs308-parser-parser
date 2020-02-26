package slogo.logicalcontroller;

import slogo.logicalcontroller.command.Command;
import slogo.logicalcontroller.variable.Variable;
import slogo.model.ModelCollection;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * Experimental Parser created by alex during some late night inspiration...
 * @author Alex Xu
 */
public class TestParser {

    private String lang;
    private Map<String, String> commandArray;


    private Stack<String> nestedCommands;
    private Stack<String> nestedValues;
    private ArrayList<Command> commandObjs;


    private ResourceBundle resources;
    private HashMap<String, String> type1;
    private HashSet<String> type2;

    private ModelCollection model;
    private List<Variable> variables;
    private List<String> command_input;
    private Command latestCommand;

    private List<Command> compiledCommandsList;


    public TestParser(String language) throws IOException {
        this.lang = language;
        commandArray = new HashMap<String, String>();
        nestedCommands = new Stack<String>();
        nestedValues = new Stack<String>();
        commandObjs = new ArrayList<Command>();
        FileInputStream fis = new FileInputStream("resources/languages/"+this.lang+".properties");
        resources = new PropertyResourceBundle(fis);
        genCommandArray();
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

    public void set(List<String> commands, ModelCollection modelCollection, List<Variable> inputVariables){
        command_input = commands;
        model = modelCollection;
        variables = inputVariables;

        latestCommand = null;

        compile();
    }

    public void executeNextCommand(){

    }

    public ModelCollection getModel(){
        return model;
    }

    public Command getCommand(){
        return latestCommand;
    }

    private void compile(){

    }

}
