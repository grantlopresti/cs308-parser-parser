package slogo.logicalcontroller.codefilters;

import java.util.ResourceBundle;
import java.util.regex.Pattern;

/**
 * Class whose purpose is to serve as a module ot the Master Filter. Must contain a filter method.
 * This VariableCreation filter specifically assumes that rogue spaces are removed before hand. If not, this filter may
 * behave in an undesirable manner.
 * @author Alex Xu
 */
public class VariableCreationFilter extends FilterSuperclass{

    public final static String VARIABLE_SEPARATOR = ":";
    public final static String VARIABLE_PROPERTIES_KEY = "MakeVariable";

    public VariableCreationFilter(){
        super();
    }

    @Override
    public String filter(String input, ResourceBundle language) {
        StringBuilder processedResult = new StringBuilder();

        String[] lineDelimited = input.split(NEW_LINE);
        for(String line : lineDelimited){
            if(line.contains(VARIABLE_SEPARATOR)){
                String newLine = retrieveProcessedLine(line, language);
                newLine = newLine + NEW_LINE;
                processedResult.append(newLine);
            }
            else{
                processedResult.append(line);
            }
        }

        return processedResult.toString();
    }

    private String retrieveProcessedLine(String line, ResourceBundle language){
        String[] lineArray = line.split(SPACE);
        StringBuilder result = new StringBuilder();

        for(int i = 0; i<lineArray.length-1; i++){
            String word = lineArray[i];
            String nextWord = lineArray[i+1];
            if(isVariableCreation(word, nextWord, language)){
                result.append(word);
                result.append(nextWord+SPACE);
                i++;
            }
            else{
                result.append(word + SPACE);
            }
        }
        result.append(lineArray[lineArray.length-1]);
        return result.toString();
    }

    private boolean isVariableCreation(String word, String nextWord, ResourceBundle language){
        String value = language.getString(VARIABLE_PROPERTIES_KEY);
        return nextWord.contains(VARIABLE_SEPARATOR) && Pattern.matches(value, word);
    }
}

