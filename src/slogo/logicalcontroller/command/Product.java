package slogo.logicalcontroller.command;

public class Product implements Command {
    private double value;

    public Product(String inputvalue){
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
