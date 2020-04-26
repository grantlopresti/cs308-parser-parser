package slogo;

import javafx.stage.Stage;
import slogo.logicalcontroller.LogicalController;
import slogo.logicalcontroller.variable.VariableList;
import slogo.model.ModelCollection;
import slogo.model.ModelTurtle;
import slogo.view.windows.SlogoView;
import slogo.visualcontroller.VisualController;

/**
 * Purpose of this class is to manage the controllers and the model.
 * An essential part of our SlogoView project, the Manager class not only creates the main
 * SlogoView GUI, but also passes around the other essential components in our design loop so
 * that they can remain private, but still communicate with each other.
 *
 */
public class Manager {
    private String myDefaultLang;

    private ModelCollection myModelCollection;
    private VariableList myVariables;

    private SlogoView mySlogoView;
    private VisualController myVisualController;
    private LogicalController myLogicalController;

    /**
     * Creates all of the seperate Slogo Components with the specified settings, and then starts
     * the application
     * @param defaultLang
     */
    public Manager(String defaultLang) {
        myDefaultLang = defaultLang;
        createModel();
        createVisualController();
        createLogicalController();
        createSlogoView();
        setViewControllerView();
        startView();
    }

    /**
     * Starts the SlogoView GUI
     *
     * @author Grant LoPresti
     */
    private void startView() {
        mySlogoView.start(new Stage());
    }

    /**
     * Allows the Visual Controller to communicate directly with the GUI
     *
     * @author Grant LoPresti
     */
    private void setViewControllerView() {
        myVisualController.setSlogoView(mySlogoView);
    }

    /**
     * Allows the GUI to pass changes made by the user to both the logical and visual controllers
     *
     * @author Grant LoPresti
     */
    private void createSlogoView() {
        mySlogoView = new SlogoView(myLogicalController, myVisualController);
    }

    private void createLogicalController() {
        myLogicalController = new LogicalController(myModelCollection, myVisualController, myVariables);
        myLogicalController.setLanguage(myDefaultLang);
    }

    private void createVisualController() {
        myVisualController = new VisualController();
    }

    private void createModel() {
        myModelCollection = new ModelCollection();
        myModelCollection.append(new ModelTurtle());
        myVariables = new VariableList();
    }

}
