package slogo.logicalcontroller.command.querie;

import slogo.exceptions.InvalidCommandException;
import slogo.logicalcontroller.command.Command;
import slogo.model.ModelTurtle;

import java.lang.reflect.Method;
import java.util.ResourceBundle;

/**
 * Abstract class for Querie Command. Takes 0 arguments
 * @author Alex Xu
 */
public abstract class QuerieCommand implements Command {
    public static final String RESOURCE_BUNDLE_LOCATION = "src/properties/querieCommands.MyBundle";

    private ResourceBundle methodMappings;

    public QuerieCommand(){
        methodMappings = ResourceBundle.getBundle(RESOURCE_BUNDLE_LOCATION);
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
            Method method = turtle.getClass().getMethod(name.toLowerCase());
            return (String) method.invoke(turtle);
        } catch (Exception e) {
            throw new InvalidCommandException();
        }
    }
}
