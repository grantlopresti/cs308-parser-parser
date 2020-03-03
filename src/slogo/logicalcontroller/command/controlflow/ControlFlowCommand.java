package slogo.logicalcontroller.command.controlflow;

import slogo.logicalcontroller.command.Command;

/**
 * Abstract Class for Control flow. In the Fetch - Interpret - Execute cycle, the ControlFlow commands act as
 * "factory" classes that generate "unraveled" SLogo code for code replacement in the Parser.
 * @author Alex Xu
 */
public abstract class ControlFlowCommand implements Command {
    private String body;
    private String unraveledCode;

    public ControlFlowCommand(String rawInput){
        body = rawInput;
    }

    public abstract void unravelCode();

    protected void setUnraveledCode(String input){
        unraveledCode = input;
    }

    public String getUnraveledCode(){
        return unraveledCode;
    }

    protected String getBody(){
        return body;
    }

    /**
     * Always returns 0
     * @return
     */
    @Override
    public double getValue() {return 0;}

    @Override
    public String getCommandType() {
        String className = this.getClass().getSimpleName();
        return className;
    }
}
