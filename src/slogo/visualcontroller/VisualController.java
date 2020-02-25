package slogo.visualcontroller;

import slogo.logicalcontroller.command.Command;
import slogo.model.ModelTurtle;
import slogo.view.windows.SlogoView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VisualController {

  private double myAnimationRate = 0.0;
  private final SlogoView mySlogoView;

  // Currently mirroring structure of VisualizationPane.java
  // TODO: Update lines to queues, turtles to map (with ID)
  // TODO: Have view controller send data, functions, and errors directly to view
  private Map<Integer, VisualTurtle> myTurtles = new HashMap<>();
  private List<VisualCommand> myCommands = new ArrayList<>();
  private List<VisualError> myErrors = new ArrayList<>();
  private List<VisualUserFunction> myFunctions = new ArrayList<>();
  private List<VisualLine> myLines = new ArrayList<>();
  private List<VisualData> myData = new ArrayList<>();

  // TODO - Refactor to appropriate location (in command logical controller  package?)
  enum CommandName {FORWARD, BACKWARD, LEFT, RIGHT;}

  /**
   * Constructor for a VisualController, with its associated SlogoView
   * @param view is the view in which VisualObjects will be added to the display
   */
  public VisualController(SlogoView view){
    this.mySlogoView = view;
  }

  /**
   * Public method callable by the logical controller to adjust the animation rate
   * @param rate is the new animation rate for adding lines into the view
   */
  public void setAnmiationRate(double rate) {
    this.myAnimationRate = rate;
  }

  /**
   * Called by the logical controller to update turtle state and draw shapes in Slogo view
   * @param turtle model turtle that is currently being acted on
   * @param command to determine how the turtle changes
   * TODO: Update switch to reflection, review tutorials and ask Alex for advice
   */
  public void appendToView(ModelTurtle turtle, Command command) {
    VisualTurtle visualTurtle = addTurtleToMap(turtle);
    visualTurtle.updateVisualTurtle(turtle);
    switch (command.toString()) {
      case("Forward"):
      case("Backward"):
        if (turtle.isPenActive()) {
          myLines.add(new VisualLine(visualTurtle));
        }
        break;
    }
  }

  private VisualTurtle addTurtleToMap(ModelTurtle turtle) {
    myTurtles.putIfAbsent(turtle.getID(), new VisualTurtle(turtle));
    return myTurtles.get(turtle.getID());
  }

}
