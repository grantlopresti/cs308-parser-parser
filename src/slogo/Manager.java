package slogo;

import javafx.application.Platform;
import javafx.stage.Stage;
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
                this.mySlogoView.start(new Stage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void setViewControllerView() {
        this.myVisualController.setSlogoView(this.mySlogoView);
    }

    private void createSlogoView() {
        this.mySlogoView = new SlogoView(this.myLogicalController);
    }

    private void createLogicalController() throws IOException {
        this.myLogicalController = new LogicalController(this.myModelCollection, this.myVisualController, this.myVariables);
        this.myLogicalController.setLanguage(DEFAULT_LANG);
    }

    private void createVisualController() {
        this.myVisualController = new VisualController();
    }

    private void createModel() {
        this.myModelCollection = new ModelCollection();
        // this.myModelCollection.append(new ModelTurtle());
        this.myVariables = new ArrayList<Variable>();
    }

}
