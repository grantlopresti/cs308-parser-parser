package slogo.xmlfactories;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import org.xml.sax.SAXException;
import slogo.exceptions.InvalidXMLConfigException;


/**
 * Purpose of this class is to validate a XML file based on an XSD Schema
 * @author Alex Xu
 */
public class XMLValidator {
    public static final String XSD_SCHEMA_FILEPATH = "src\\slogo\\xmlfactories\\workspaceSchema.xsd";
    public static final String INVALID_INSTANTIATION_ERROR = "Instantiating utility class.";

    private XMLValidator(){
        throw new AssertionError(INVALID_INSTANTIATION_ERROR);
    }

    /**
     * Validates a XML file against the XSD file that is given as part of the program.
     * @param xmlFile the file to be validated
     * @return true if the document structure is valid, false otherwise.
     */
    public static boolean validateXMLStructure(File xmlFile) throws InvalidXMLConfigException{
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(XSD_SCHEMA_FILEPATH));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(xmlFile));
        }catch(IOException | SAXException e){
            throw new InvalidXMLConfigException();
        }
        return true;
    }
}
