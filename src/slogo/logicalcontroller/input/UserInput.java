package slogo.logicalcontroller.input;

import slogo.exceptions.InvalidCommandException;
import slogo.logicalcontroller.BundleInterface;
import slogo.logicalcontroller.command.Command;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.*;

public class UserInput implements UserInputInterface, BundleInterface {

    private List<String> myUserInput;
    private ResourceBundle myResources;

    private static final String SUPERCLASS_PROPERTIES = "src/properties/commandSuperclass.properties";
    private static final String PARAMETER_PROPERTIES = "src/properties/parameterCount.properties";
    private static final String SLOGO_COMMAND = "slogo.logicalcontroller.command.";
    private static ResourceBundle myCommandMap;
    private static ResourceBundle myParameterMap;

    public UserInput(List<String> userInput, ResourceBundle bundle) {
        this.myUserInput = userInput;
        this.myResources = bundle;
        try {
            this.myCommandMap = BundleInterface.createResourceBundle(SUPERCLASS_PROPERTIES);
            this.myParameterMap = BundleInterface.createResourceBundle(PARAMETER_PROPERTIES);
        } catch (IOException e) {
            // TODO - FIX THIS
            e.printStackTrace();
        }
    }

    @Override
    public Command getNextCommand() {
        int lineIndex = findNextLine();
        int commandIndex = findLastCommand(lineIndex);
        System.out.printf("found next command @line %d \n", lineIndex);
        System.out.printf("found last command @index %d \n", commandIndex);
        String command = "vpered";
        String translated = translateCommand(command);
        System.out.printf("translated %s to %s", command, translated);
        int params = countParameters(translated);
        System.out.printf("requires %d parameters", params);
        List<String> arguments = getArguments(lineIndex, commandIndex, params);
        String superclass = getCommandSuperclass(translated);
        Command c = createCommand(superclass, translated, arguments);
        return c;
    }

    @Override
    public void setCodeReplacement(List<String> code) {

    }

    private int findNextLine() {
        for(int i = 0; i < this.myUserInput.size(); i++){
            String s = this.myUserInput.get(i);
            if(s.split("\\s+").length > 1){
                return i;
            }
        }
        return 0;
    }

    public int findLastCommand(int index) {
        String line = this.myUserInput.get(index);
        String[] words = line.split("\\s+");
        for(int i = words.length-1; i>=0; i--){
            if(isValidCommand(words[i])) {
                return i;
            }
        }
        return -1;
    }

    private String getLine(int index) {
        return this.myUserInput.get(index);
    }

    private List<String> getArguments(int line, int index, int params) {
        index ++;
        String input = this.myUserInput.get(line);
        String[] words = input.split("\\s");
        int stop = index+params;
        String[] sub = Arrays.copyOfRange(words, index, stop);
        System.out.println("Printing arguments: ");
        for (String s: sub) {System.out.println(s);}
        return new ArrayList<String>(List.of(sub));
    }

    private boolean isValidCommand(String s) {
        ResourceBundle bundle = this.myResources;
        Enumeration<String> resourceEnumeration = bundle.getKeys();
        String key; String value;
        while (resourceEnumeration.hasMoreElements()) {
            key = resourceEnumeration.nextElement();
            value = bundle.getString(key);
            if (value.contains(s)) {return true;}
        }
        return false;
    }

    /**
     * Translates raw user inputted command (in arbitrary language) to Key in properties file
     * @param command
     * @return
     * TODO - what to do if command not found? - throw no command exception
     */
    private String translateCommand(String command) {
        Enumeration<String> resourceEnumeration = this.myResources.getKeys();
        String key; String value;
        while (resourceEnumeration.hasMoreElements()) {
            key = resourceEnumeration.nextElement();
            value = this.myResources.getString(key);
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

    private int countParameters(String translated) {
        return Integer.parseInt(this.myParameterMap.getString(translated));
    }

}
