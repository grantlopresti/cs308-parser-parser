package slogo.logicalcontroller.input;

import java.util.*;

public class UserInput implements UserInputInterface {

    private List<String> myUserInput;
    private ResourceBundle myResources;

    public UserInput(List<String> userInput, ResourceBundle bundle) {
        this.myUserInput = userInput;
        this.myResources = bundle;
    }

    @Override
    public int findNextLine() {
        for(int i = 0; i < this.myUserInput.size(); i++){
            String s = this.myUserInput.get(i);
            if(s.split("\\s+").length > 1){
                return i;
            }
        }
        return 0;
    }

    @Override
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

    @Override
    public String getLine(int index) {
        return this.myUserInput.get(index);
    }

    @Override
    public List<String> getArguments(int line, int index, int params) {
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

}
