package slogo.logicalcontroller.command.querie;

import slogo.exceptions.InvalidCommandException;
import slogo.exceptions.ResourceBundleException;
import slogo.logicalcontroller.BundleInterface;
import slogo.logicalcontroller.command.Command;
import slogo.logicalcontroller.variable.VariableList;
import slogo.model.ModelTurtle;

import java.lang.reflect.Method;
import java.util.ResourceBundle;

/**
 * Abstract class for Querie Command. Takes 0 arguments
 * @author Alex Xu
 */
public abstract class QuerieCommand implements Command {

    private static final String RESOURCE_BUNDLE_LOCATION = "src/properties/querieCommands.properties";
    private ResourceBundle methodMappings;

    public QuerieCommand(){
        try {
            this.methodMappings = BundleInterface.createResourceBundle(RESOURCE_BUNDLE_LOCATION);
        } catch (Exception e) {
            throw new ResourceBundleException("could not find querie properties file");
        }
    }

    /**
     * Returns the method name to be called on turtle objects, based on a properties file.
     */
    private String getMethodName(){
        String key = this.getCommandType();
        return methodMappings.getString(key);
    }

    @Override
    public String getCommandType() {
        String className = this.getClass().getSimpleName();
        return className;
    }

    /**
     * Returns QuerieCommand name using reflection.
     * @return
     */
    public String getCommandCategory(){
        String className = this.getClass().getSuperclass().getSimpleName();
        return className;
    }

    @Override
    public String toString(){
        return getMethodName();
    }

    public String execute(ModelTurtle turtle){
        try {
            String name = this.getMethodName();
            Method method = turtle.getClass().getMethod(name);
            Object result = method.invoke(turtle);
            return result.toString();
        } catch (Exception e) {
            throw new InvalidCommandException();
        }
    }

    @Override
    public String execute(VariableList list) {
        return null;
    }
}
