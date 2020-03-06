package slogo.visualcontroller;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import slogo.exceptions.LogicalException;
import slogo.logicalcontroller.command.Command;
import slogo.logicalcontroller.variable.Variable;
import slogo.logicalcontroller.variable.VariableList;
import slogo.model.ModelCollection;
import slogo.model.ModelTurtle;
import slogo.view.TurtleImage;
import slogo.view.windows.SlogoView;

import java.util.*;

public class VisualController implements VisualInterface {

  private double myAnimationRate = 0.0;
  private SlogoView mySlogoView;

  // Currently mirroring structure of VisualizationPane.java (change to bindings)
  private Map<Integer, VisualTurtle> myTurtles = new HashMap<Integer, VisualTurtle>();
  private ObservableList<VisualTurtle> myTurtlesList = FXCollections.observableArrayList(myTurtles.values());

  private List<VisualLine> myLines = new ArrayList<>();

  private SimpleObjectProperty<ObservableList<VisualError>> myErrorsProperty;
  private SimpleObjectProperty<ObservableList<VisualCommand>> myCommandsProperty;
  private SimpleObjectProperty<ObservableList<VisualUserFunction>> myFunctionsProperty;
  private SimpleObjectProperty<ObservableList<VisualData>> myDataProperty;
  private SimpleObjectProperty<ObservableList<VisualVariable>> myVariablesProperty;
  private SimpleObjectProperty<ObservableList<VisualFile>> myFilesProperty;
  private SimpleObjectProperty<ObservableList<String>> myTurtleNamesProperty;

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
    myTurtleNamesProperty = new SimpleObjectProperty<>(FXCollections.observableArrayList());
  }

  @Override
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

  public void start(ModelCollection model) {
    Iterator iter = model.iterator();
    while (iter.hasNext()) {
      moveTurtle((ModelTurtle) iter.next());
    }
  }

  // TODO - implement commands updating as strings
  @Override
  public void update(ModelCollection model, VariableList variableList, Command command) {
    moveModelObject(model, command);
    updateVariables(variableList);
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

  @Override
  public void changeTurtleImage(String newValue) {
    TurtleImage image = TurtleImage.valueOf(newValue);
    for (Integer i : myTurtles.keySet()) {
      System.out.printf("INDEX: ", i);
      myTurtles.get(i).setImage(image);
    }
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

  private void updateVariables(VariableList variableList) {
    myVariablesProperty.getValue().clear();
    if (!variableList.isEmpty()) {
      Iterator iter = variableList.iterator();
      while (iter.hasNext()) {
        myVariablesProperty.getValue().add(new VisualVariable((Variable)iter.next()));
      }
    }
  }

  // TODO: leverage command knowledge to dictate turtle motion (rotations specifically)
  private void moveModelObject(ModelCollection modelCollection, Command command) {
    Iterator iter = modelCollection.iterator();
    while (iter.hasNext()) {
      ModelTurtle turtle = (ModelTurtle) iter.next();
      moveTurtle(turtle);
    }
  }

  @Override
  public void updateCommand(String userInput) {
    FXCollections.reverse(this.myCommandsProperty.getValue());
    this.myCommandsProperty.getValue().add(new VisualCommand(userInput));
    FXCollections.reverse(this.myCommandsProperty.getValue());
  }

  private void moveTurtle(ModelTurtle turtle) {
    VisualTurtle visualTurtle = addTurtleToMap(turtle);
    myTurtlesList.add(visualTurtle);
    visualTurtle.updateVisualTurtle(turtle);
    try {
      mySlogoView.updateVisualTurtles(new ArrayList<>(List.of(visualTurtle)));
      System.out.println("attempted to update visual turtle");
      if (turtle.isPenActive())
        appendLine(new VisualLine(visualTurtle));
    } catch (NullPointerException e) {
      e.printStackTrace();
      System.out.println("Given null turtle set, passing on draw");
    }
  }

  private void appendLine(VisualLine line) {
    myLines.add(line);
    mySlogoView.updateVisualLines(new ArrayList<VisualLine>(List.of(line)));
  }

  private VisualTurtle addTurtleToMap(ModelTurtle turtle) {
    myTurtles.putIfAbsent(turtle.getID(), new VisualTurtle());
    myTurtleNamesProperty.getValue().add(turtle.getID() + "");
    return myTurtles.get(turtle.getID());
  }

  public ObservableValue<? extends ObservableList<String>> getMyTurtlesProperty() {
    return myTurtleNamesProperty;
  }
}
