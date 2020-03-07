package slogo.logicalcontroller.command.controlflow;

import java.util.ArrayList;
import java.util.List;


public class MakeUserInstruction extends ControlFlowCommand {
    private String funcName;
    private List<String> body;


    public MakeUserInstruction(List<List<String>> rawInput){
        super(rawInput.get(1));

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