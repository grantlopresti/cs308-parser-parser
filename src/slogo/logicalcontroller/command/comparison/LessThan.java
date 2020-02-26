package slogo.logicalcontroller.command.comparison;

import slogo.logicalcontroller.command.Command;

public class LessThan extends ComparisonCommand {
    private double value;

    public LessThan(String inputValue1, String inputValue2){
        super(inputValue1, inputValue2);
    }

    @Override
    public void performComparison() {
        if(argument1 < argument2){
            setReturnValue(1);
        }
        else{
            setReturnValue(0);
        }
    }

}
