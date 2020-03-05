package slogo.logicalcontroller.input;

import slogo.exceptions.InvalidCommandException;
import slogo.exceptions.NoCommandFound;
import slogo.logicalcontroller.BundleInterface;
import slogo.logicalcontroller.command.Command;
import slogo.logicalcontroller.command.controlflow.ControlFlowCommand;
import slogo.logicalcontroller.command.controlflow.ControlFlowExtractor;

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

    private int controlFlowEndIndex;

    private static final String SUPERCLASS_PROPERTIES = "src/properties/commandSuperclass.properties";
    private static final String PARAMETER_PROPERTIES = "src/properties/parameterCount.properties";
    private static final String REPLACE_PROPERTIES = "src/properties/lineReplace.properties";
    private static final String SLOGO_COMMAND = "slogo.logicalcontroller.command.";
    private static final String CONTROLFLOW = "controlflow";
    private static ResourceBundle myCommandMap;
    private static ResourceBundle myParameterMap;
    private static ResourceBundle myReplaceMap;

    public UserInput(List<String> userInput, ResourceBundle bundle) {
        this.myUserInput = userInput;
        this.myResources = bundle;
        try {
            this.myCommandMap = BundleInterface.createResourceBundle(SUPERCLASS_PROPERTIES);
            this.myParameterMap = BundleInterface.createResourceBundle(PARAMETER_PROPERTIES);
            this.myReplaceMap = BundleInterface.createResourceBundle(REPLACE_PROPERTIES);
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
            String superclass = getCommandSuperclass(translated);
            System.out.printf("translated %s to %s \n", this.myCommand, translated);
            if (superclass.equals(CONTROLFLOW)) {
                List<List<String>> args = getControlFlowArguments(this.myLineIndex, this.myCommandIndex, translated);
                return createControlCommand(superclass, translated, args);
            } else {
                int params = countParameters(translated);
                List<String> args = getArguments(this.myLineIndex, this.myCommandIndex, params);
                return createCommand(superclass, translated, args);
            }
        } catch (NoCommandFound e) {
            throw new NoCommandFound("Could not generate command");
        }
    }

    private Command createControlCommand(String superclass, String command, List<List<String>> args) {
        try {
            Class clazz = Class.forName(createCommandPath(superclass, command));
            Constructor ctor = clazz.getConstructor(List.class);
            return (Command) ctor.newInstance(args);
        } catch (Exception e) {
            throw new InvalidCommandException("Could not create control flow command");
        }
    }

    // TODO - implement method stub - PLEase double check - By Alex
    private List<List<String>> getControlFlowArguments(int myLineIndex, int myCommandIndex, String command) {
        controlFlowEndIndex = 0;

        List<List<String>> returnList = new ArrayList<>();
        int numParams = Integer.parseInt(myParameterMap.getString(command).split(",")[1]);
        int numBracketSets = Integer.parseInt(myParameterMap.getString(command).split(",")[0]);

        System.out.println("This Command Object Takes " + numParams + " Parameters");
        System.out.println("This Command Object Takes " + numBracketSets + " Sets of Bracketed Bodies");

        if(numParams == 1){
            List<String> paramsList = new ArrayList<>();
            String param = myUserInput.get(myLineIndex).split(" ")[myCommandIndex + 1];
            paramsList.add(param);
            returnList.add(paramsList);
        }

        int lineLocation = -1;          //TODO: Default values that are not necessarily used.
        int columnLocation = -1;
        int linePointer = myLineIndex;
        for(int i = 0; i<numBracketSets; i++){
            int[] locationArray = getOpenBracketIndex(linePointer);
            lineLocation = locationArray[0];
            columnLocation = locationArray[1];
            System.out.println("Opening Bracket at :" + lineLocation + " " + columnLocation);
            List<String> argumentSet = ControlFlowExtractor.initControlFlow(myUserInput, lineLocation, columnLocation);
            returnList.add(argumentSet);
            linePointer += argumentSet.size();
            controlFlowEndIndex = ControlFlowExtractor.getLineLastBrac(myUserInput, lineLocation, columnLocation);
        }

        return returnList;
    }

    private int[] getOpenBracketIndex(int lineIndex){                     //TODO: Searches for the first openbracket after the given line index, so line index should be the smallest index of a possible occurance.
        int[] result = new int[2];                                        //TODO: Index 0 is the Lineindex, 1 is the locationIndex in that line.
        for(int j = lineIndex; j<myUserInput.size(); j++) {
            String line = myUserInput.get(j);

            String[] lineElements = line.split(" ");
            System.out.println("Looking for bracket on line: " + j);
            for (int i = 0; i < lineElements.length; i++) {
                System.out.print(lineElements[i]);
                if (lineElements[i].equals("[")) {
                    result[0] = j;
                    result[1] = i;
                    return result;
                }
            }
        }

        System.out.println("NO OPENING BRACKET FOUND");
        result[0] = -1;
        result[1] = -1;
        return result;
    }

    @Override
    public boolean isFinished() {
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
    public void setCodeReplacement(List<String> code, Command command) {
        System.out.println("Command Type Detected: " + command.getClass().getSuperclass().getSimpleName());
        if (command.getClass().getSuperclass().getSimpleName().equals("ControlFlowCommand")) {
            multiLineReplace(command);
        } else {
            singleLineReplace(code);
        }
    }

    private void singleLineReplace(List<String> code) {
        StringBuilder sb = new StringBuilder(this.myPrefix);
        for (String s: code) {
            sb.append(SPACE + s);
        }
        sb.append(SPACE + this.mySuffix);
        this.myUserInput.set(this.myLineIndex, sb.toString().trim());
        System.out.printf("code replaced to: %s", this.myUserInput.get(this.myLineIndex));
    }

    // TODO: Implementation can be better?
    private void multiLineReplace(Command command) {
        System.out.println("Starting multi-line replace...");

        ControlFlowCommand myControlFlowCommand = (ControlFlowCommand) command;
        List<String> code = myControlFlowCommand.getUnraveledCode();

        System.out.println("Code to be used to replace: //");
        for(String s : code){
            System.out.println(s);
        }
        System.out.println("//");

        List<String> prefix = myUserInput.subList(0, myLineIndex);

        /*
        System.out.println("Looking for end bracket starting on line " + controlFlowEndIndex);

        while(!myUserInput.get(controlFlowEndIndex).contains("]")){
            controlFlowEndIndex++;
        }
        System.out.println("Closing Bracket at Line: " + controlFlowEndIndex);


         */

        List<String> suffix = myUserInput.subList(controlFlowEndIndex+1, myUserInput.size());

        List<String> result = new ArrayList<>();
        result.addAll(prefix);
        result.addAll(code);
        result.addAll(suffix);

        myUserInput = result;
    }

    private int findNextLine() {
        for(int i = 0; i < this.myUserInput.size(); i++){
            String s = this.myUserInput.get(i);
            if(s.split("\\s+").length > 1){
                return i;
            }
        }
        throw new NoCommandFound();
    }

    private int findLastCommand(int index) {
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

    // TODO - assume that parameters are space separated (good enough assumption)
    private List<String> getArguments(int lineIndex, int commandIndex, int params) {
        int stop = commandIndex+1+params;
        String input = this.myUserInput.get(lineIndex);
        String[] words = input.split("\\s");
        String[] args = Arrays.copyOfRange(words, commandIndex+1, stop);
        this.myPrefix = spaceSeparatedString(Arrays.copyOfRange(words, 0, commandIndex));
        this.mySuffix = spaceSeparatedString(Arrays.copyOfRange(words, stop, words.length));
        this.myUserInput.set(lineIndex, input);
        return new ArrayList<String>(List.of(args));
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

    /**
     * Translates raw user inputted command (in arbitrary language) to Key in properties file
     * @param command
     * @return
     * TODO - what to do if command not found? - throw no command exception
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
        // System.out.printf("returning path: %s \n", path);
        return path;
    }

    private int countParameters(String translated) {
        return Integer.parseInt(this.myParameterMap.getString(translated));
    }

}
