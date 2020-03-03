package slogo.logicalcontroller.command.modifier;

import slogo.logicalcontroller.command.Command;

import java.util.ResourceBundle;

/**
 * Abstract class for Modifier Commands.
 * Takes in 0, 1, or 2 input commands.
 * @author Alex Xu
 */
public abstract class ModifierCommand implements Command {
    public static final String RESOURCE_BUNDLE_LOCATION = "src/properties/modifierCommands.MyBundle";

    private ResourceBundle methodMappings;

    protected double argument1;
    protected double argument2;

    private int numArguments;

    public ModifierCommand(){
        methodMappings = ResourceBundle.getBundle(RESOURCE_BUNDLE_LOCATION);
        numArguments = 0;
    }

    public ModifierCommand(String input1){
        this();
        argument1 = Double.parseDouble(input1);
        numArguments = 1;
    }

    public ModifierCommand(String input1, String input2){
        this(input1);
        argument2 = Double.parseDouble(input2);
        numArguments = 2;
    }

    /**
     * Returns the method name to be called on turtle objects, based on a properties file.
     */
    public String getMethodName(){
        String key = this.getCommandType();
        return methodMappings.getString(key);
    }

    @Override
    public String getCommandType() {
        String className = this.getClass().getSimpleName();
        return className;
    }

    /**
     * Returns ModifierCommand name using reflection.
     * @return
     */
    public String getCommandCategory(){
        String className = this.getClass().getSuperclass().getSimpleName();
        return className;
    }

    /**
     *To String Method
     * @return                                      //TODO: May need to refactor this to avoid multiple if statements
     */
    public String toString(){
        String returnString = this.getCommandType();

        if(numArguments >= 1){
            returnString = returnString + " " + argument1;
        }
        else if(numArguments >= 2){
            returnString = returnString + " " + argument2;
        }

        return returnString;
        
    }

    public double getArgument1(){
        return argument1;
    }

    public double getArgument2(){
        return argument2;
    }
}