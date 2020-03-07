package slogo.logicalcontroller.codefilters;

import slogo.exceptions.InvalidFilterException;
import slogo.exceptions.ResourceBundleException;
import slogo.logicalcontroller.BundleInterface;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
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
public final class MasterCodeFilterUtility {

    public static final String RESOURCE_BUNDLE_LOCATION = "src/properties/inputFilters.properties";
    public static final String INVALID_INSTANTIATION_ERROR = "Instantiating utility class.";
    public static final String CLASS_PREFIX = "slogo.logicalcontroller.codefilters.";
    public static final String LANGUAGES_PATH = "resources/languages/";
    public static final String PROPERTIES_SUFFIX = ".properties";

    /**
     * Applies all of the filters, user-defined through a properties file.
     */
    private MasterCodeFilterUtility(){
        throw new AssertionError(INVALID_INSTANTIATION_ERROR);
    }

    public static String filter(String rawInput, String language){
        printInput(rawInput);
        String result;

        ResourceBundle filtersApplied;
        ResourceBundle languageBundle;
        try {
            filtersApplied = BundleInterface.createResourceBundle(RESOURCE_BUNDLE_LOCATION);
            languageBundle = BundleInterface.createResourceBundle(LANGUAGES_PATH + language + PROPERTIES_SUFFIX);
        } catch (Exception e) {
            throw new ResourceBundleException();
        }

        try {
            List<String> activeFilters = extractActiveFilters(filtersApplied);
            List<Class> activeFilterClasses = extractOperatingClasses(activeFilters);

            List<FilterSuperclass> filterObjectsList = extractFilterObjects(activeFilterClasses);
            List<Method> methodList = extractOperatingMethods(activeFilterClasses);

            result = performFiltering(filterObjectsList, methodList, rawInput, languageBundle);
            return result;
        }
        catch(ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e){
            throw new InvalidFilterException();
        }
    }

    private static List<FilterSuperclass> extractFilterObjects(List<Class> activeFilters) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        List<FilterSuperclass> filterObjectsList = new ArrayList<>();
        for(Class myClass : activeFilters){
            Constructor filterConstructor = myClass.getConstructor(null);
            Object obj = filterConstructor.newInstance(null);
            FilterSuperclass filterObject = (FilterSuperclass) obj;
            filterObjectsList.add(filterObject);
        }
        return filterObjectsList;
    }

    private static String performFiltering(List<FilterSuperclass> classList, List<Method> methodList, String rawInput, ResourceBundle language) throws InvocationTargetException, IllegalAccessException {
        String result = rawInput;
        for(int i = 0; i<classList.size(); i++){
            Object resultObject = methodList.get(i).invoke(classList.get(i), result, language);
            result = (String) resultObject;
        }
        return result;
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
        printActiveFilters(activeFiltersList);                                                  //TODO: Remove the print statement later
        return activeFiltersList;
    }

    private static List<Method> extractOperatingMethods(List<Class> classList) throws NoSuchMethodException {
        List<Method> methodList = new ArrayList<>();
        for (Class myClass : classList){
            Method filterMethod = myClass.getMethod("filter", String.class, ResourceBundle.class);            //TODO: Use reflection to extract this "filter" name.
            methodList.add(filterMethod);
        }
        return methodList;
    }

    private static List<Class> extractOperatingClasses(List<String> activeFilters) throws ClassNotFoundException {             //TODO: Error handling
        List<Class> classList = new ArrayList<Class>();
        for (String className : activeFilters){
            Class classObject = Class.forName(createClassPath(CLASS_PREFIX, className));
            classList.add(classObject);
        }
        return classList;
    }

    private static String createClassPath(String prefix, String className){
        return prefix + className;
    }

    //TODO: Remove this print statement after debugging and things work.
    private static void printActiveFilters(List<String> activeFiltersList){
        System.out.println("ACTIVE FILTERS DETECTED: ");
        for (String item : activeFiltersList){
            System.out.println(item);
        }
    }

    private static void printInput(String input){
        System.out.println("RAW INPUT: ");
        System.out.println(input);
    }
}