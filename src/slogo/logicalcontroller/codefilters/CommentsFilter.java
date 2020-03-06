package slogo.logicalcontroller.codefilters;

/**
 * Class whose purpose is to serve as a module ot the Master Filter. Must contain a filter method.
 * @author Alex Xu
 */
public class CommentsFilter extends FilterSuperclass {

    public final static String COMMENT_SYMBOL = "#";
    public final static String EMPTY_STRING = "";

    /**
     * Default constructor of a Filter Object
     */
    public CommentsFilter() {
        super();
    }

    @Override
    public String filter(String input) {
        StringBuilder processedResult = new StringBuilder();

        String[] lineDelimited = input.split(NEW_LINE);
        for(String line : lineDelimited){
            if(line.contains(COMMENT_SYMBOL)){
                String newLine = extractUncommentedPart(line);
                processedResult.append(newLine);
            }
            else{
                processedResult.append(line + NEW_LINE);
            }
        }
        return processedResult.toString();
    }

    private String extractUncommentedPart(String line){
        int commentIndex = line.indexOf(COMMENT_SYMBOL);
        String newLine = line.substring(0, commentIndex);
        if(newLine.equals(EMPTY_STRING)){
            return newLine;
        }
        else{
            return newLine + NEW_LINE;
        }
    }
}
