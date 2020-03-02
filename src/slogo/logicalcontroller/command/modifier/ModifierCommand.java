package slogo.logicalcontroller.command.modifier;

import slogo.logicalcontroller.command.Command;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * Abstract class for Modifier Commands.
 * Takes in 0, 1, or 2 input commands.
 * @author Alex Xu
 */
public abstract class ModifierCommand implements Command {
    //public static final String RESOURCE_BUNDLE_LOCATION = "properties/modifierCommands.MyBundle";

    private ResourceBundle methodMappings;
    private double returnValue;

    protected double argument1;
    protected double argument2;

    public ModifierCommand(){
        try {
            methodMappings = new PropertyResourceBundle(new FileInputStream("src/properties/modifierCommands.properties"));
        } catch (IOException e) {
            System.out.println("File not found exception");
        }
    }

    public ModifierCommand(String input1){
        this();
        argument1 = Double.parseDouble(input1);
        System.out.println("Arg 1:" + argument1);
    }

    public ModifierCommand(String input1, String input2){
        this(input1);
        argument2 = Double.parseDouble(input2);
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
     * Returns ModifierCommand name using reflection.
     * @return
     */
    public String getCommandCategory(){
        String className = this.getClass().getSuperclass().getSimpleName();
        return className;
    }

    /**
     *To String Method
     * @return
     */
    public String toString(){
        return (this.getCommandType() + " " + this.getValue());
    }
}