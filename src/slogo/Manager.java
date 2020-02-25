package slogo;

import slogo.logicalcontroller.LogicalController;
import slogo.model.ModelCollection;
import slogo.model.ModelTurtle;
import slogo.view.windows.SlogoView;
import slogo.visualcontroller.VisualController;

/**
 * Purpose of this class is to manage the controllers and the model.
 * NOTE: Not complete and will only be continued to work on if we have time by basic implementation due date.
 */
public class Manager {
    private ModelCollection myModelCollection;
    private SlogoView mySlogoView;
    private VisualController myVisualController;
    private LogicalController myLogicalController;

    public Manager(){
        myModelCollection = new ModelCollection();
        myModelCollection.append(new ModelTurtle());
    }

    public void receiveCommand(String command){
        //LogicalController.handleNewCommand(command, myModelCollection);

    }



    //TODO:
    /*
    1. Launch the GUI, with Manager passed in?
    2.
     */
}
