package slogo.logicalcontroller.variable;

import slogo.exceptions.ResourceBundleException;
import slogo.logicalcontroller.command.Command;
import slogo.model.ModelCollection;

import java.io.IOException;
import java.util.List;

public interface ParserInterface {

    void executeNextCommand();

    Command getLatestCommand();

    VariableList getVariables();

    boolean isFinished();

    ModelCollection getModel();

    void parse(List<String> lines) throws ResourceBundleException;

    void setLanguage(String language) throws IOException;

}
