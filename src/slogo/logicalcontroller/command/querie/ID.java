package slogo.logicalcontroller.command.querie;

import slogo.logicalcontroller.command.Command;

import java.util.List;

public class ID extends QuerieCommand {
    private double myValue;

    public ID(List<String> args){
        super();
    }

    public double getValue() {
        return myValue;
    }

    @Override
    public String getCommandType() {
        return "ID";
    }
}
