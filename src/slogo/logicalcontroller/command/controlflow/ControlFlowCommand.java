package slogo.logicalcontroller.command.controlflow;

import slogo.logicalcontroller.command.Command;

import java.util.List;

/**
 * Abstract Class for Control flow. In the Fetch - Interpret - Execute cycle, the ControlFlow commands act as
 * "factory" classes that generate "unraveled" SLogo code for code replacement in the Parser.
 * @author Alex Xu
 */
public abstract class ControlFlowCommand implements Command {
    private List<String> body;
    private List<String> unraveledCode;

    public ControlFlowCommand(List<String> rawInput){
        body = rawInput;
        unravelCode();
    }

    public List<String> getUnraveledCode(){
        return unraveledCode;
    }

    @Override
    public String getCommandType() {
        String className = this.getClass().getSimpleName();
        return className;
    }

    protected List<String> getBody(){
        return body;
    }

    protected abstract void unravelCode();

    protected void setUnraveledCode(List<String> input){
        unraveledCode = input;
    }
}
