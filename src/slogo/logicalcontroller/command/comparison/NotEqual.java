package slogo.logicalcontroller.command.comparison;


public class NotEqual extends ComparisonCommand {
    private double value;

    public NotEqual(String inputValue1, String inputValue2){
        super(inputValue1, inputValue2);
    }

    @Override
    public void performComparison() {

    }
}
