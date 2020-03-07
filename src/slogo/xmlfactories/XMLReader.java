package slogo.xmlfactories;

/**
 * XML Reader reads an XML and returns Workspace data
 * @author Alex Xu
 */
public final class XMLReader {
    public static final String INVALID_INSTANTIATION_ERROR = "Instantiating utility class.";

    private XMLReader(){
        throw new AssertionError(INVALID_INSTANTIATION_ERROR);
    }
}
