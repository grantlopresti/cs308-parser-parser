package slogo.logicalcontroller.command.modifier;

import slogo.logicalcontroller.command.Command;
import slogo.model.ModelTurtle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * Abstract class for Modifier Commands.
 * Takes in 0, 1, or 2 input commands.
 * @author Alex Xu
 */
public abstract class ModifierCommand implements Command, ModifierInterface {
    public static final String RESOURCE_BUNDLE_LOCATION = "src/properties/modifierCommands.MyBundle";

    private FileInputStream fis;
    private ResourceBundle methodMappings;

    protected double argument1;
    protected double argument2;

    private String userInput;

    public ModifierCommand(){
        try {
            fis = new FileInputStream("src/properties/modifierCommands.properties");
        } catch (FileNotFoundException e) {
            System.out.println("File not found exception");
        }
        try {
            methodMappings = new PropertyResourceBundle(fis);
        } catch (IOException e) {
            System.out.println("IO Exception");
        }
    }

    public ModifierCommand(String input1){
        this();
        argument1 = Double.parseDouble(input1);
    }

    public ModifierCommand(String input1, String input2){
        this(input1);
        argument2 = Double.parseDouble(input2);
    }

    public void setUserInput(String input){
        userInput = input;
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

    @Override
    public abstract String toString();

    public double getArgument1(){
        return argument1;
    }

    public double getArgument2(){
        return argument2;
    }

    @Override
    public abstract void execute(ModelTurtle turtle);

    @Override
    public abstract String codeReplace();

}