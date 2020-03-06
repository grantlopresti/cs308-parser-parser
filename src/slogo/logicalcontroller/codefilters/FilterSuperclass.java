package slogo.logicalcontroller.codefilters;

/**
 * Abstract superclass for all Filter Modules
 * @author Alex Xu
 */
public abstract class FilterSuperclass implements FiltersInterface{

    /**
     * Default constructor of a Filter Object
     */
    public FilterSuperclass(String input){
    }

    public String filter(String input){
        return input;
    }

}