package slogo.logicalcontroller.codefilters;

/**
 * Utility class whose purpose is to serve as a module ot the Master Filter. Must contain a filter method.
 * @author Alex Xu
 */
public class LeadingSpaceFilter extends FilterSuperclass {
    public final static String NEW_LINE = "\n";

    public LeadingSpaceFilter(){
        super();
    }

    @Override
    public String filter(String input) {
        StringBuilder processedResult = new StringBuilder();

        String[] lineDelimited = input.split(NEW_LINE);

        return processedResult.toString();
    }
}
