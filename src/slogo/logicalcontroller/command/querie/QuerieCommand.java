package slogo.logicalcontroller.command.querie;

import slogo.logicalcontroller.command.Command;
import slogo.model.ModelTurtle;

import java.util.ResourceBundle;

/**
 * Abstract class for Querie Command. Takes 0 arguments
 * @author Alex Xu
 */
public abstract class QuerieCommand implements Command {
    public static final String RESOURCE_BUNDLE_LOCATION = "src/properties/querieCommands.MyBundle";

    private ResourceBundle methodMappings;
    private String userInput;

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

    public void setUserInput(String input){
        userInput = input;
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
        return userInput;
    }

    public abstract void execute(ModelTurtle turtle);

    public abstract String codeReplace();
}
