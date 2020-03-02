package slogo.logicalcontroller.command.querie;

import slogo.logicalcontroller.command.Command;

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
    public String getMethodName(){
        String key = this.getCommandType();
        return methodMappings.getString(key);
    }

    /**
     * Always returns 0
     * @return
     */
    @Override
    public double getValue() {return 0;}

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
}
