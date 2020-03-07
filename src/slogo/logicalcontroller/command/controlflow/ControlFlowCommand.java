package slogo.logicalcontroller.command.controlflow;

import slogo.logicalcontroller.command.Command;
import slogo.logicalcontroller.variable.VariableList;
import slogo.model.ModelTurtle;

import java.util.List;

/**
 * Abstract Class for Control flow. In the Fetch - Interpret - Execute cycle, the ControlFlow commands act as
 * "factory" classes that generate "unraveled" SLogo code for code replacement in the Parser.
 * @author Alex Xu
 */
public abstract class ControlFlowCommand implements Command {
    private List<String> myBody;
    private List<String> unraveledCode;

    public ControlFlowCommand(List<String> rawInput){
        System.out.println("creating control flow command from initial body: ");
        for (String s: rawInput) {System.out.println(s);}
        this.myBody = rawInput;
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
        return myBody;
    }

    protected abstract void unravelCode();

    protected void setUnraveledCode(List<String> input){
        unraveledCode = input;
    }

    @Override
    public String execute(ModelTurtle turtle) {
        return null;
    }

    @Override
    public String execute(VariableList list) {
        return null;
    }
}
