package slogo.logicalcontroller.codefilters;

import java.util.ResourceBundle;

/**
 * Class whose purpose is to serve as a module ot the Master Filter. Must contain a filter method.
 * @author Alex Xu
 */
public class LeadingTrailingSpaceFilter extends FilterSuperclass {

    public LeadingTrailingSpaceFilter(){
        super();
    }

    @Override
    public String filter(String input, ResourceBundle language) {
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
