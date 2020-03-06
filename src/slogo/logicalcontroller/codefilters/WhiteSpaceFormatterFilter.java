package slogo.logicalcontroller.codefilters;

/**
 * Class whose purpose is to serve as a module ot the Master Filter. Must contain a filter method.
 * @author Alex Xu
 */
public class WhiteSpaceFormatterFilter extends FilterSuperclass{

    public WhiteSpaceFormatterFilter(){
        super();
    }

    @Override
    public String filter(String input) {
        StringBuilder processedResult = new StringBuilder();

        String[] lineDelimited = input.split(NEW_LINE);
        for(String line : lineDelimited){
            String newLine = line.replaceAll(" +", " ");
            newLine = newLine + NEW_LINE;
            processedResult.append(newLine);
        }

        return processedResult.toString();
    }
}
