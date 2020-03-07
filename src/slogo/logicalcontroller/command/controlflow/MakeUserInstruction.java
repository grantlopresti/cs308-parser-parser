package slogo.logicalcontroller.command.controlflow;

import slogo.model.ModelTurtle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MakeUserInstruction extends ControlFlowCommand {
    private String funcName;
    private List<String> body;
    private List<String> variables;


    public MakeUserInstruction(List<List<String>> rawInput){
        super(rawInput.get(2));
        System.out.println("Arg 2 results: " + rawInput.get(1));
        System.out.println("Arg 3 results: " + rawInput.get(2));
        funcName = rawInput.get(0).get(0);
        variables = rawInput.get(1);
        unravelCode();
    }

    public String getName(){
        return this.funcName;
    }

    @Override
    public List<String> getUnraveledCode(){
        System.out.println("getUnraveledCode results: " + Arrays.asList(getBody()));
        return getBody();

    }

    @Override
    protected void unravelCode() {
        setUnraveledCode(body);
    }
}