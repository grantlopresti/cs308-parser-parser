package slogo.logicalcontroller.codefilters;

import slogo.exceptions.ResourceBundleCreationException;
import slogo.logicalcontroller.BundleInterface;

import java.util.ResourceBundle;

/**
 * Utility class that filters the user input before sending it to the parser.
 * final keyword prevents subclassing and improves efficiency.
 * @author Alex Xu
 */
public final class MasterCodeFilter {

    private static final String RESOURCE_BUNDLE_LOCATION = "src/properties/inputFilters.properties";

    private MasterCodeFilter(){
        throw new AssertionError("Instantiating utility class.");
    }

    public static String filterComments(String rawInput){
        String result;

        ResourceBundle filtersApplied;
        try {
            filtersApplied = BundleInterface.createResourceBundle(RESOURCE_BUNDLE_LOCATION);
        } catch (Exception e) {
            throw new ResourceBundleCreationException();
        }



    }
}
