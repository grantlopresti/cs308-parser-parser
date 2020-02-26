package slogo.logicalcontroller.command.comparison;

/**
 * Model concrete implementation of the Comparison Command type.
 */
public class And extends ComparisonCommand {

    public And(String inputValue1, String inputValue2){
        super(inputValue1, inputValue2);
    }

    @Override
    public void performComparison(){
        if(argument1 != 0 && argument2 != 0){
            setReturnValue(1);
        }
        else{
            setReturnValue(0);
        }
    }
}
