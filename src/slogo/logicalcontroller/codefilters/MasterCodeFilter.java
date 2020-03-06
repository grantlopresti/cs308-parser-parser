package slogo.logicalcontroller.codefilters;

import slogo.exceptions.ResourceBundleCreationException;
import slogo.logicalcontroller.BundleInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * Utility class that filters the user input before sending it to the parser.
 * final keyword prevents subclassing and improves efficiency.
 * @author Alex Xu
 */
public final class MasterCodeFilter {

    private static final String RESOURCE_BUNDLE_LOCATION = "src/properties/inputFilters.properties";

    /**
     * Applies all of the filters, user-defined through a properties file.
     */
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

        List<String> activeFilters = extractActiveFilters(filtersApplied);

    }

    private static List<String> extractActiveFilters(ResourceBundle filterInformation){
        List<String> activeFiltersList = new ArrayList<>();

        Set<String> keys = filterInformation.keySet();
        for(String key : keys){
            String value = filterInformation.getString(key);
            boolean activeState = Boolean.parseBoolean(value);
            if(activeState){
                activeFiltersList.add(key);
            }
        }
        return activeFiltersList;
    }
}
