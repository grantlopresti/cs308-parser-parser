package slogo.logicalcontroller.codefilters;

import slogo.exceptions.ResourceBundleException;
import slogo.logicalcontroller.BundleInterface;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * Utility class that filters the user input before sending it to the parser.
 * final keyword prevents subclassing and improves efficiency.
 * @author Alex Xu
 */
public final class MasterCodeFilter{

    public static final String RESOURCE_BUNDLE_LOCATION = "src/properties/inputFilters.properties";
    public static final String INVALID_INSTANTIATION_ERROR = "Instantiating utility class.";
    public static final String CLASS_PREFIX = "slogo.logicalcontroller.codefilters.";

    /**
     * Applies all of the filters, user-defined through a properties file.
     */
    private MasterCodeFilter(){
        throw new AssertionError(INVALID_INSTANTIATION_ERROR);
    }

    public static String filter(String rawInput){
        String result;
        ResourceBundle filtersApplied;
        try {
            filtersApplied = BundleInterface.createResourceBundle(RESOURCE_BUNDLE_LOCATION);
        } catch (Exception e) {
            throw new ResourceBundleException();
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

        printActiveFilters(activeFiltersList);                  //TODO: Remove the print statement later

        return activeFiltersList;
    }

    private List<Method> extractOperatingMethods(List<String> activeFilters) throws ClassNotFoundException, NoSuchMethodException {
        List<Method> methodList = new ArrayList<>();
        List<Class> classList = extractOperatingClasses(activeFilters);
        for (Class myClass : classList){
            Method filterMethod = myClass.getMethod("filter", String.class);
            methodList.add(filterMethod);
        }
        return methodList;
    }

    private List<Class> extractOperatingClasses(List<String> activeFilters) throws ClassNotFoundException {             //TODO: Error handling
        List<Class> classList = new ArrayList<Class>();
        for (String className : activeFilters){
            Class classObject = Class.forName(createClassPath(CLASS_PREFIX, className));
            classList.add(classObject);
        }
        return classList;
    }

    private String createClassPath(String prefix, String className){
        return prefix + className;
    }

    //TODO: Remove this print statement after debugging and things work.
    private static void printActiveFilters(List<String> activeFiltersList){
        System.out.println("ACTIVE FILTERS DETECTED: ");
        for (String item : activeFiltersList){
            System.out.println(item);
        }
    }

}