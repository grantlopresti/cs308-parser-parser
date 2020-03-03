package slogo.visualcontroller;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.scene.image.ImageView;
import slogo.exceptions.LogicalException;
import slogo.logicalcontroller.command.Command;
import slogo.logicalcontroller.variable.Variable;
import slogo.model.ModelCollection;
import slogo.model.ModelTurtle;
import slogo.view.TurtleImage;
import slogo.view.windows.SlogoView;

import java.util.*;

public class VisualController implements VisualInterface {

  private double myAnimationRate = 0.0;
  private SlogoView mySlogoView;

  // Currently mirroring structure of VisualizationPane.java (change to bindings)
  private Map<Integer, VisualTurtle> myTurtles = new HashMap<>();
  private List<VisualLine> myLines = new ArrayList<>();

  private SimpleObjectProperty<ObservableList<VisualError>> myErrorsProperty;
  private SimpleObjectProperty<ObservableList<VisualCommand>> myCommandsProperty;
  private SimpleObjectProperty<ObservableList<VisualUserFunction>> myFunctionsProperty;
  private SimpleObjectProperty<ObservableList<VisualData>> myDataProperty;
  private SimpleObjectProperty<ObservableList<VisualVariable>> myVariablesProperty;
  private SimpleObjectProperty<ObservableList<VisualFile>> myFilesProperty;

  /**
   * Constructor for a VisualController, with its associated SlogoView
   * @param view is the view in which VisualObjects will be added to the display
   */
  public VisualController(SlogoView view){
    mySlogoView = view;
  }

  public VisualController() {
    initProperties();
  }

  private void initProperties() {
    myErrorsProperty = new SimpleObjectProperty<>(FXCollections.observableArrayList());
    myCommandsProperty = new SimpleObjectProperty<>(FXCollections.observableArrayList());
    myFunctionsProperty = new SimpleObjectProperty<>(FXCollections.observableArrayList());
    myDataProperty = new SimpleObjectProperty<>(FXCollections.observableArrayList());
    myVariablesProperty = new SimpleObjectProperty<>(FXCollections.observableArrayList());
    myFilesProperty = new SimpleObjectProperty<>(FXCollections.observableArrayList());
  }

  public void setSlogoView(SlogoView view) {
    mySlogoView = view;
  }

  /**
   * Public method callable by the logical controller to adjust the animation rate
   * @param rate is the new animation rate for adding lines into the view
   * TODO - implement animation rate of object addition via queueing/threading (not threading lol)
   */
  @Override
  public void setAnimationRate(double rate) {
    myAnimationRate = rate;
  }

  @Override
  public void update(ModelCollection model, List<Variable> variableList, Command latestCommand) {
    moveModelObject(model);
    updateVariables(variableList);
  }

  /**
   * Called by the logical controller to update turtle state and draw shapes in Slogo view
   * @param modelCollection model turtle that is currently being acted on
   * TODO - Add casting try catch
   */

  public void moveModelObject(ModelCollection modelCollection) {
    Iterator iter = modelCollection.iterator();
    while (iter.hasNext()) {
      ModelTurtle turtle = (ModelTurtle) iter.next();
      moveTurtle(turtle);
    }
  }

  /**
   * Called by Logical Controller after a successful command execution
   * @param command String representation of prior command execution
   */

  public void updateCommands(String command) {
    FXCollections.reverse(myCommandsProperty.getValue());
    myCommandsProperty.getValue().add(new VisualCommand(command));
    FXCollections.reverse(myCommandsProperty.getValue());
  }

  /**
   *
   * @param e Exception that was just thrown by the logical controller
   * TODO - Incorporate error severity into logical controller error creation
   */
  @Override
  public void updateErrors(LogicalException e) {
    VisualError error = new VisualError(e);
    myErrorsProperty.getValue().add(error);
    mySlogoView.announceError(error);
  }


  public void updateVariables(Variable v) {
    VisualVariable var = new VisualVariable(v);
    myVariablesProperty.getValue().add(var);
  }

  @Override
  public Property getProperty(VisualProperty type) {
    switch (type) {
      case COMMAND:
        return myCommandsProperty;
      case DATA:
        return myDataProperty;
      case VARIABLE:
        return myVariablesProperty;
      case ERROR:
        return myErrorsProperty;
      case FUNCTION:
        return myFunctionsProperty;
      case FILE:
        return myFilesProperty;
      default:
        throw new IllegalArgumentException();
    }
  }

  private void moveTurtle(ModelTurtle turtle) {
    VisualTurtle visualTurtle = addTurtleToMap(turtle);
    visualTurtle.setChangeState(true);
    visualTurtle.updateVisualTurtle(turtle);
    try {
      mySlogoView.updateVisualTurtles(new ArrayList<>(List.of(visualTurtle)));
      if (turtle.isPenActive())
        appendLine(new VisualLine(visualTurtle));
    } catch (NullPointerException e) {
      System.out.println("Given null turtle set, passing on draw");
    }

  }

  private void appendLine(VisualLine line) {
    myLines.add(line);
    mySlogoView.updateVisualLines(new ArrayList<VisualLine>(List.of(line)));
  }

  private VisualTurtle addTurtleToMap(ModelTurtle turtle) {
    myTurtles.putIfAbsent(turtle.getID(), new VisualTurtle());
    return myTurtles.get(turtle.getID());
  }

  public void changeTurtleImage(String newValue) {
    TurtleImage image = TurtleImage.valueOf(newValue);
    myTurtles.get(0).setImage(image.getImagePath());
  }
}
