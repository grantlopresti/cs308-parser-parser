package slogo.logicalcontroller.command.comparison;

import slogo.logicalcontroller.command.Command;
import slogo.model.ModelTurtle;

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

    protected double argument1;
    protected double argument2;

    public ComparisonCommand(String input1){
        argument1 = Double.parseDouble(input1);
    }

    public ComparisonCommand(String input1, String input2){
        argument1 = Double.parseDouble(input1);
        argument2 = Double.parseDouble(input2);
    }

    @Override
    public String getCommandType() {
        String className = this.getClass().getSimpleName();
        return className;
    }

    /**
     * Method that an external parser should call to retrieve the return value to conduct code replace.
     * @return
     */
    public abstract String execute(ModelTurtle turtle);

    protected String booleanToString(boolean value) {
        return ""+(value ? 1 : 0);
    }
}
