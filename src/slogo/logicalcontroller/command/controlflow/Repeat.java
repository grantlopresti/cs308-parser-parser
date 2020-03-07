package slogo.logicalcontroller.command.controlflow;

import slogo.model.ModelTurtle;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements the REPEAT Factory Design Pattern
 * @author Alex Xu
 */
public class Repeat extends ControlFlowCommand {
    private int myRepeatCount;

    /**
     * Constructor for the Repeat object, takes in the number of repeats desired, rounds to nearest integer.
     * @param rawInput
     */
    public Repeat(List<List<String>> rawInput){
        super(rawInput.get(1));
        System.out.println("Creating repeat command \n called constructor");
        System.out.printf("rawInput.get(0).get(0)=%s", rawInput.get(0).get(0));
        this.myRepeatCount = (int)Math.round(Double.parseDouble(rawInput.get(0).get(0)));
        System.out.println("myRepeatCount: " + this.myRepeatCount);

        unravelCode();
    }

    @Override
    protected void unravelCode() {
        List<String> myBody = this.getBody();
        List<String> result = new ArrayList<>();
        //result.add("set :repcount " + this.myRepeatCount);

        for(int i = 0; i < this.myRepeatCount; i++){
            result.addAll(myBody);
        }

        setUnraveledCode(result);

        System.out.println("From Command Object's Perspective, Body is");
        for(String s : result){
            System.out.println(s);
        }
    }

    @Override
    public String execute(ModelTurtle turtle) {
        return null;
    }
}
