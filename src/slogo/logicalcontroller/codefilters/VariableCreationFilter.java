package slogo.logicalcontroller.codefilters;

/**
 * Class whose purpose is to serve as a module ot the Master Filter. Must contain a filter method.
 * This VariableCreation filter specifically assumes that rogue spaces are removed before hand. If not, this filter may
 * behave in an undesirable manner.
 * @author Alex Xu
 */
public class VariableCreationFilter extends FilterSuperclass{       //TODO: This filter will also need to take in a language in order to detect the variable creation keyword.

    public final static String VARIABLE_SEPARATOR = ":";

    public VariableCreationFilter(){                                //TODO: Initialize the resource bundle here. MAYBE THE BEST WAY IS TO HAVE THE MASTER FILTER INITIALIZE THE BUNDLE.
        super();
    }

    @Override
    public String filter(String input) {
        StringBuilder processedResult = new StringBuilder();

        String[] lineDelimited = input.split(NEW_LINE);
        for(String line : lineDelimited){
            if(line.contains(VARIABLE_SEPARATOR)){
                String newLine = retrieveProcessedLine(line);
                newLine = newLine + NEW_LINE;
                processedResult.append(newLine);
            }
            else{
                processedResult.append(line);
            }
        }

        return processedResult.toString();
    }

    private String retrieveProcessedLine(String line){
        String[] lineArray = line.split(SPACE);
        StringBuilder result = new StringBuilder();

        for(int i = 1; i<lineArray.length; i++){
            String word = lineArray[i];
            String previousWord = lineArray[i-1];
            if(isVariableCreation(word, previousWord)){

            }
        }

        return result.toString();
    }

    private boolean isVariableCreation(String word, String previousWord){
        return word.contains(VARIABLE_SEPARATOR); //&& previousWord.equals()
    }

}

