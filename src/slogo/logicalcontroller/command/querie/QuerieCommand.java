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
    private double returnValue;

    public QuerieCommand(){
        methodMappings = ResourceBundle.getBundle(RESOURCE_BUNDLE_LOCATION);
    }

    protected void setReturnValue(double value){
        returnValue = value;
    }

    /**
     * Returns the method name to be called on turtle objects, based on a properties file.
     */
    public String getMethodName(){
        String key = this.getCommandType();
        return methodMappings.getString(key);
    }

    @Override
    public double getValue() {
        return this.returnValue;
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
}
