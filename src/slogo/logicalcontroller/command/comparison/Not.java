package slogo.logicalcontroller.command.comparison;

public class Not extends ComparisonCommand {

    public Not(String inputValue){
        super(inputValue);
    }

    @Override
    protected void performComparison() {
        if(argument1 == 0){
            setReturnValue(1);
        }
        else{
            setReturnValue(0);
        }
    }
}
