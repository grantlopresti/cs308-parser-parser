package slogo.logicalcontroller.command.teller;

import slogo.model.ModelCollection;
import slogo.model.ModelObject;
import slogo.model.ModelTurtle;

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
        // System.out.printf("executing tell: returning last turtle id (%d)", last);
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
        for (int id: myArgs) {
            model.append(new ModelTurtle(id));
            model.activate(id);
        }
    }

    private void deactivateTurtles(ModelCollection model) {
        Map<Integer, ModelObject> map = model.getModelMap();
        for (ModelObject object: map.values()) {
            int id = object.getID();
            if (!myArgs.contains(id)) {
                model.deactivate(id);
            }
        }
    }

}
