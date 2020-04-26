package slogo.xmlfactories;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import slogo.exceptions.InvalidXMLConfigException;
import slogo.logicalcontroller.variable.Variable;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * XMLFactory reads in Workspace Data and saves an XML File
 * @author Alex Xu
 */
public final class XMLFactory {
    private XMLFactory(){}

    public static void createXML(String filepath, List<Variable> variablesList, List<String> commandsList){
        File myFile = new File(filepath);
        Document doc;

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;

        try {
            builder = factory.newDocumentBuilder();
            doc = builder.parse(myFile);
        } catch (SAXException | IOException | ParserConfigurationException e) {
            throw new InvalidXMLConfigException();
        }
        doc.getDocumentElement().normalize();
    }
}