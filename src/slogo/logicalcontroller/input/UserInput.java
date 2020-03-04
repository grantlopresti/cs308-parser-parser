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
    private String myPrefix;
    private String mySuffix;
    private static final int NONE_FOUND = -1;
    private static final String SPACE = " ";

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
            String translated = translateCommand(this.myCommand);
            System.out.printf("translated %s to %s \n", this.myCommand, translated);
            int params = countParameters(translated);
            List<String> arguments = getArguments(this.myLineIndex, this.myCommandIndex, params);
            String superclass = getCommandSuperclass(translated);
            Command c = createCommand(superclass, translated, arguments);
            return c;
        } catch (NoCommandFound e) {
            throw new NoCommandFound("Could not generate command");
        }
    }

    // TODO - add to interface
    public boolean isFinished() {
        System.out.println("checking if user input is finished");
        try {
            this.myLineIndex = findNextLine();
            this.myCommandIndex = findLastCommand(this.myLineIndex);
            return false;
        } catch (NoCommandFound e) {
            return true;
        }
    }

    // TODO - how to handle multi line replacements vs. single line?
    // FOR NOW - assuming single line replace (only modifiers and math will work)
    @Override
    public void setCodeReplacement(List<String> code) {
        StringBuilder sb = new StringBuilder(this.myPrefix);
        for (String s: code) {
            sb.append(SPACE + s);
        }
        sb.append(SPACE + this.mySuffix);
        this.myUserInput.set(this.myLineIndex, sb.toString().trim());
        System.out.printf("code replaced to: %s", this.myUserInput.get(this.myLineIndex));
    }

    // TODO - handle edge case of no more lines (raise flag when no more next lines and no more last commands?)
    private int findNextLine() {
        System.out.printf("looking for nextLine on input: %s \n", this.myUserInput.get(0));
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
        System.out.printf("looking for lastCommand on input: %s \n", this.myUserInput.get(0));
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
    // TODO - assume that parameters are space separated (good enough assumption)
    private List<String> getArguments(int lineIndex, int commandIndex, int params) {
        // traverseUserInput();
        int stop = commandIndex+1+params;
        String input = this.myUserInput.get(lineIndex);
        String[] words = input.split("\\s");
        String[] args = Arrays.copyOfRange(words, commandIndex+1, stop);
        this.myPrefix = spaceSeparatedString(Arrays.copyOfRange(words, 0, commandIndex));
        this.mySuffix = spaceSeparatedString(Arrays.copyOfRange(words, stop, words.length));
        this.myUserInput.set(lineIndex, input);
        return new ArrayList<String>(List.of(args));
    }

    private void traverseUserInput() {
        System.out.println("Traversing user input: ");
        for (String s: this.myUserInput) {
            System.out.println(s);
        }
    }

    private String spaceSeparatedString(String[] fullCommand) {
        if (fullCommand.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String s: fullCommand) {
            sb.append(s + SPACE);
        }
        return sb.deleteCharAt(sb.length()-1).toString();
    }

    private boolean isValidCommand(String s) {
        ResourceBundle bundle = this.myResources;
        for(String key: Collections.list(bundle.getKeys())){
            String regex = bundle.getString(key);
            String[] regexElems = regex.split("\\|");
            if(regexElems[0].equals(s) || ((regexElems.length >1) && regexElems[1].equals(s))){
                return true;
            }
        }
        return false;
    }

    // TODO - implement rgular expression mathcing to fix false triggering of errors
    private boolean keyContains(String s, String value) {
        System.out.println("String s: " + s);
        System.out.println("Value v: " + value);
        Set<String> options = new HashSet<String>();
        options.addAll(List.of(value.split("\\\\?|")));
        options.addAll(List.of(value.split("|\\\\")));
        options.addAll(List.of(value.split("|")));
        Iterator iter = options.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
        boolean bool = options.contains(s);
        System.out.printf("value %s contains key %s is %b\n", value, s, bool);
        return bool;
    }

    /**
     * Translates raw user inputted command (in arbitrary language) to Key in properties file
     * @param command
     * @return
     * TODO - what to do if command not found? - throw no command exception
     * TODO - use regular expression mappings to populate this as well
     */
    private String translateCommand(String command) {
        ResourceBundle bundle = this.myResources;
        for(String key: Collections.list(bundle.getKeys())){
            String regex = bundle.getString(key);
            String[] regexElems = regex.split("\\|");
            if(regexElems[0].equals(command) || ((regexElems.length >1) && regexElems[1].equals(command))){
                return key;
            }
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
        System.out.printf("attempting to createCommand in UserInput.java from command: %s \n", command);
        System.out.printf("arguments (%d): ", arguments.size());
        for (String s: arguments) {
            System.out.printf("%s \n", s);
        }
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
