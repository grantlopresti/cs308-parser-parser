package slogo.visualcontroller;

import slogo.logicalcontroller.command.Command;
import slogo.model.ModelCollection;
import slogo.model.ModelObject;
import slogo.model.ModelTurtle;
import slogo.view.subsections.VisualizationPane;
import slogo.view.windows.SlogoView;

import java.util.*;

public class VisualController {

  private double myAnimationRate = 0.0;
  private SlogoView mySlogoView;

  // Currently mirroring structure of VisualizationPane.java
  // TODO: Update lines to queues, turtles to map (with ID) - check with Grant in VisPane to match structure
  // TODO: Have view controller send data, functions, and errors directly to view? Could send here first for styling
  private Map<Integer, VisualTurtle> myTurtles = new HashMap<>();
  private List<VisualLine> myLines = new ArrayList<>();
  private List<VisualCommand> myCommands = new ArrayList<>();
  private List<VisualError> myErrors = new ArrayList<>();
  private List<VisualUserFunction> myFunctions = new ArrayList<>();
  private List<VisualData> myData = new ArrayList<>();

  // TODO - Refactor to appropriate location (in command logical controller  package?), may need for reflection
  enum CommandName {FORWARD, BACKWARD, LEFT, RIGHT;}
  private static final String FORWARD = "Forward";
  private static final String BACKWARD = "Backward";


  /**
   * Constructor for a VisualController, with its associated SlogoView
   * @param view is the view in which VisualObjects will be added to the display
   */
  public VisualController(SlogoView view){
    this.mySlogoView = view;
  }

  public VisualController() {

  }

  public void setSlogoView(SlogoView view) {
    this.mySlogoView = view;
  }

  /**
   * Public method callable by the logical controller to adjust the animation rate
   * @param rate is the new animation rate for adding lines into the view
   * TODO - implement animation rate of object addition via queueing/threading (not threading lol)
   */
  public void setAnimationRate(double rate) {
    this.myAnimationRate = rate;
  }

  /**
   * Called by the logical controller to update turtle state and draw shapes in Slogo view
   * @param modelCollection model turtle that is currently being acted on
   * TODO - Update switch to reflection based on object type
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
    myCommands.add(new VisualCommand(command));
  }

  /**
   *
   * @param e Exception that was just thrown by the logical controller
   * TODO - Incorporate error severity into logical controller error creation
   */
  public void updateErrors(RuntimeException e) {
    myErrors.add(new VisualError(e.toString(), ErrorSeverity.BASIC));
  }

  private void moveTurtle(ModelTurtle turtle) {
    VisualTurtle visualTurtle = addTurtleToMap(turtle);
    visualTurtle.updateVisualTurtle(turtle);
    System.out.println(visualTurtle.toString());
    try {
      mySlogoView.updateVisualTurtles(new ArrayList<VisualTurtle>(List.of(visualTurtle)));
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
    myTurtles.putIfAbsent(turtle.getID(), new VisualTurtle(turtle));
    return myTurtles.get(turtle.getID());
  }

}
