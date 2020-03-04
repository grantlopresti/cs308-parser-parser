package slogo.logicalcontroller.command.math;

import slogo.logicalcontroller.command.Command;

import java.util.List;

public class Product extends MathCommand {

    public Product(List<String> input){
        super(input.get(0), input.get(1));
    }

    @Override
    public String execute() {
        return Double.toString(this.myArgument1 * this.myArgument2);
    }

    @Override
    public String getCommandType() {
        return "Product";
    }
}
