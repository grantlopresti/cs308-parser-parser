package slogo.logicalcontroller.command.math;

import slogo.logicalcontroller.command.Command;

public class Product extends MathCommand {
    private double value;

    public Product(String inputvalue){
        super(inputvalue);
        value = Double.parseDouble(inputvalue);

    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String getCommandType() {
        return "Product";
    }
}
