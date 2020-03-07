package slogo.logicalcontroller.command.controlflow;

import slogo.model.ModelTurtle;

import java.util.ArrayList;
import java.util.List;


public class MakeUserInstruction extends ControlFlowCommand {
    private String funcName;
    private List<String> body;
    private List<String> variables;


    public MakeUserInstruction(List<List<String>> rawInput){
        super(rawInput.get(2));
        funcName = rawInput.get(0).get(0);
        variables = rawInput.get(1);

        unravelCode();
    }

    public String getName(){
        return this.funcName;
    }

    @Override
    protected void unravelCode() {
        setUnraveledCode(body);
    }
}