package slogo.logicalcontroller.codefilters;

/**
 * Abstract superclass for all Filter Modules
 * @author Alex Xu
 */
public abstract class FilterSuperclass implements FiltersInterface{

    public final static String NEW_LINE = "\n";

    /**
     * Default constructor of a Filter Object
     */
    public FilterSuperclass(){
    }

    public abstract String filter(String input);

}