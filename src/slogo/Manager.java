package slogo;

import javafx.application.Platform;
import javafx.stage.Stage;
import slogo.exceptions.InvalidCommandException;
import slogo.logicalcontroller.LogicalController;
import slogo.logicalcontroller.command.Command;
import slogo.logicalcontroller.variable.Variable;
import slogo.model.ModelCollection;
import slogo.model.ModelTurtle;
import slogo.view.windows.SlogoView;
import slogo.visualcontroller.VisualController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import slogo.visualcontroller.VisualError;

/**
 * Purpose of this class is to manage the controllers and the model.
 * NOTE: Not complete and will only be continued to work on if we have time by basic implementation due date.
 */
public class Manager {
    public static final String DEFAULT_LANG = "ENGLISH";
    private ModelCollection myModelCollection;
    private List<Variable> myVariables;
    private SlogoView mySlogoView;
    private VisualController myVisualController;
    private LogicalController myLogicalController;

    public Manager() throws IOException {
        createModel();
        createVisualController();
        createLogicalController();
        createSlogoView();
        setViewControllerView();
        startView();
    }

    private void startView() {
        Platform.startup(() -> {
            try {
                mySlogoView.start(new Stage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void setViewControllerView() {
        myVisualController.setSlogoView(mySlogoView);
    }

    private void createSlogoView() {
        mySlogoView = new SlogoView(myLogicalController, myVisualController);
    }

    private void createLogicalController() throws IOException {
        myLogicalController = new LogicalController(myModelCollection, myVisualController, myVariables);
        myLogicalController.setLanguage(DEFAULT_LANG);
    }

    private void createVisualController() {
        myVisualController = new VisualController();
    }

    private void createModel() {
        myModelCollection = new ModelCollection();
        myModelCollection.append(new ModelTurtle());
        myVariables = new ArrayList<Variable>();
    }

}
