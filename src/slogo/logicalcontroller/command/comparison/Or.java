package slogo.logicalcontroller.command.comparison;

import slogo.logicalcontroller.command.Command;

public class Or extends ComparisonCommand {
    private double value;

    public Or (String inputValue1, String inputValue2){
        super(inputValue1, inputValue2);
    }

    @Override
    public void performComparison() {
        if(argument1!= 0  || argument2!= 0){
            setReturnValue(1);
        }
        else{
            setReturnValue(0);
        }

    }
}
