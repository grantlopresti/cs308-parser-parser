package slogo.logicalcontroller.input;

import slogo.exceptions.InvalidCommandException;
import slogo.exceptions.NoCommandFound;
import slogo.logicalcontroller.BundleInterface;
import slogo.logicalcontroller.command.Command;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.*;

public class UserInput implements UserInputInterface, BundleInterface {

    private List<String> myUserInput;
    private ResourceBundle myResources;
    private int myLineIndex;
    private int myCommandIndex;
    private String myCommand;
    private static final int NONE_FOUND = -1;

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
        try {
            this.myLineIndex = findNextLine();
            this.myCommandIndex = findLastCommand(this.myLineIndex);
            System.out.printf("found next command @line %d \n", this.myLineIndex);
            System.out.printf("found last command @index %d \n", this.myCommandIndex);
            String translated = translateCommand(this.myCommand);
            System.out.printf("translated %s to %s \n", this.myCommand, translated);
            int params = countParameters(translated);
            System.out.printf("requires %d parameters \n", params);
            List<String> arguments = getArguments(this.myLineIndex, this.myCommandIndex, params);
            String superclass = getCommandSuperclass(translated);
            Command c = createCommand(superclass, translated, arguments);
            return c;
        } catch (NoCommandFound e) {
            throw new NoCommandFound("Could not generate command");
        }

    }

    // TODO - how to handle multi line replacements vs. single line?
    // FOR NOW - assuming single line replace (only modifiers and math will work)
    @Override
    public void setCodeReplacement(List<String> code) {
        String replace = code.get(0);
        String lineUpdate = this.myUserInput.get(this.myLineIndex) + replace;
        this.myUserInput.set(this.myLineIndex, lineUpdate);
        traverseUserInput();
    }

    // TODO - handle edge case of no more lines (raise flag when no more next lines and no more last commands?)
    private int findNextLine() {
        for(int i = 0; i < this.myUserInput.size(); i++){
            String s = this.myUserInput.get(i);
            if(s.split("\\s+").length > 1){
                return i;
            }
        }
        throw new NoCommandFound();
    }

    // TODO - handle no more commands in the line
    public int findLastCommand(int index) {
        String line = this.myUserInput.get(index);
        String[] words = line.split("\\s+");
        for(int i = words.length-1; i>=0; i--){
            if(isValidCommand(words[i])) {
                this.myCommand = words[i];
                return i;
            }
        }
        throw new NoCommandFound();
    }

    private String getLine(int index) {
        return this.myUserInput.get(index);
    }

    // TODO - how to handle multiple line parameters (param number is constant, could be bracketed parameter]
    // TODO - assume all parameters go until end of line? Truncating line prematurely? (maybe good enough)
    // TODO - assume that parameters are space separated (good enough assumption)
    private List<String> getArguments(int line, int index, int params) {
        index ++;
        String input = this.myUserInput.get(line);
        String[] words = input.split("\\s");
        int stop = index+params;
        String[] sub = Arrays.copyOfRange(words, index, stop);
        String[] fullCommand = Arrays.copyOfRange(words, index-1, stop);
        input = removeArguments(input, fullCommand);
        System.out.print("Printing arguments: ");
        for (String s: sub) {System.out.print(s + " ");}
        this.myUserInput.set(line, input);
        traverseUserInput();
        return new ArrayList<String>(List.of(sub));
    }

    private void traverseUserInput() {
        System.out.println("Traversing user input: ");
        for (String s: this.myUserInput) {
            System.out.println(s);
        }
    }

    // TODO - refactor these two into static methods (may change for multiline commands)
    private String removeArguments(String input, String[] sub) {
        StringBuilder sb = new StringBuilder(input);
        String remove = spaceSeparatedString(sub);
        sb.replace(input.length()-remove.length()-1, input.length(), "");
        System.out.printf("input truncated from: %s \nto: %s \n", input, sb.toString());
        return sb.toString();
    }

    private String spaceSeparatedString(String[] fullCommand) {
        StringBuilder sb = new StringBuilder();
        for (String s: fullCommand) {
            sb.append(s + " ");
        }
        System.out.printf("space separated string: %s \n", sb.toString());
        return sb.substring(0, sb.length()-2);
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
