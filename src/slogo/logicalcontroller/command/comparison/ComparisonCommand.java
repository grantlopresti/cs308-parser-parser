package slogo.logicalcontroller.command.comparison;

import slogo.logicalcontroller.command.Command;

import java.util.Arrays;
import java.util.List;

/**
 * Abstract class for Comparisons.
 * All Comparison Commands Take in 2 Arguments, except for NOT. Hence two constructors are available.
 *
 * Helpful Note on Reflection: https://stackoverflow.com/questions/6901764/get-concrete-class-name-from-abstract-class
 *
 * @author Alex Xu
 */
public abstract class ComparisonCommand implements Command {
    public static final List<Integer> POSSIBLE_VALUES = Arrays.asList(new Integer[]{0, 1});
    public static final int DEFAULT_VALUE = 0;

    private int returnValue;

    protected double argument1;
    protected double argument2;

    public ComparisonCommand(String input1){
        argument1 = Double.parseDouble(input1);
        performComparison();
    }

    public ComparisonCommand(String input1, String input2){
        argument1 = Double.parseDouble(input1);
        argument2 = Double.parseDouble(input2);
        performComparison();
    }

    protected abstract void performComparison();

    protected void setReturnValue(int value){
        if(POSSIBLE_VALUES.contains(value)){
            returnValue = value;
        }
        else{
            returnValue = DEFAULT_VALUE;
        }
    }

    public double getValue() {
        return this.returnValue;
    }

    @Override
    public String getCommandType() {
        String className = this.getClass().getSimpleName();
        return className;
    }
}
