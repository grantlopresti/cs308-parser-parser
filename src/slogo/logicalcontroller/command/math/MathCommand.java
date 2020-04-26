package slogo.logicalcontroller.command.math;

import slogo.logicalcontroller.command.Command;
import slogo.logicalcontroller.variable.VariableList;
import slogo.model.ModelTurtle;

/**
 * Abstract class for MathCommand
 * @author Math Smith, Alex Xu
 */
public abstract class MathCommand implements Command {

    private double returnValue;
    protected double myArgument1;
    protected double myArgument2;

    private String userInput;

    public MathCommand() {
    }

    public MathCommand(String input1) {
        this.myArgument1 = Double.parseDouble(input1);
    }

    public MathCommand(String input1, String input2) {
        // System.out.printf("creating 2 arg math command from %s and %s\n", input1, input2);
        this.myArgument1 = Double.parseDouble(input1);
        this.myArgument2 = Double.parseDouble(input2);
    }

    public void setUserInput(String input){
        userInput = input;
    }

    protected void setReturnValue(double value){
       returnValue = value;
    }

    public double getValue() {
        return returnValue;
    }

    @Override
    public String toString(){
        return userInput;
    }

    public abstract String execute(ModelTurtle turtle);

    @Override
    public String execute(VariableList list) {
        return null;
    }

}
