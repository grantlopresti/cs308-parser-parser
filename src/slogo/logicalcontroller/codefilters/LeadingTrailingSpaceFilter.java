package slogo.logicalcontroller.codefilters;

/**
 * Utility class whose purpose is to serve as a module ot the Master Filter. Must contain a filter method.
 * @author Alex Xu
 */
public class LeadingTrailingSpaceFilter extends FilterSuperclass {
    public final static String NEW_LINE = "\n";

    public LeadingTrailingSpaceFilter(){
        super();
    }

    @Override
    public String filter(String input) {
        StringBuilder processedResult = new StringBuilder();

        String[] lineDelimited = input.split(NEW_LINE);
        for(String line : lineDelimited){
            String newLine = line.trim();
            newLine = newLine + NEW_LINE;
            processedResult.append(newLine);
        }

        return processedResult.toString();
    }
}
