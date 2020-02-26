package slogo.logicalcontroller.command.comparison;

import slogo.logicalcontroller.command.Command;

public class Equal extends ComparisonCommand {
    private double value;

    public Equal(String inputValue1, String inputValue2){
        super(inputValue1, inputValue2);
    }

    @Override
    public void performComparison(){}
}
