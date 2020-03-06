package slogo.logicalcontroller.command.teller;

import slogo.logicalcontroller.command.Command;
import slogo.model.ModelCollection;
import slogo.model.ModelObject;
import slogo.model.ModelTurtle;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Tell extends TellerCommand {

    public Tell(List<String> args) {
        super(args);
    }

    @Override
    public String execute(ModelCollection model) {
        activateTurtles(model);
        deactivateTurtles(model);
        int last = myArgs.get(myArgs.size()-1);
        System.out.println("executing tell: returning last turtle id" + last);
        return Integer.toString(last);
    }

    @Override
    public String getCommandType() {
        return "Tell";
    }

    /**
     * Create turtles and turn them all on
     * @param model
     */
    private void activateTurtles(ModelCollection model) {
        Map<Integer, ModelObject> map = model.getModelMap();
        for (int id: myArgs) {
            ModelTurtle turtle = new ModelTurtle(id);
            map.putIfAbsent(id, turtle);
            turtle.activate();
        }
    }

    private void deactivateTurtles(ModelCollection model) {
        Map<Integer, ModelObject> map = model.getModelMap();
        for (ModelObject object: map.values()) {
            if (!myArgs.contains(object.getID())) {
                ModelTurtle turtle = (ModelTurtle) object;
                turtle.deactivate();
            }
        }
    }

}
