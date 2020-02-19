package slogo.view;

import java.util.List;
import java.util.Queue;
import slogo.controllers.visualcontroller.VisualCommand;
import slogo.controllers.visualcontroller.VisualObject;

public interface Visible {

  /**
   * Adds a Visual Object to the Current Display
   * @param visual is a single VisualObject
   */
  void append(VisualObject visual);

  /**
   * Adds multiple Visual Objects to the Current Display
   * @param visuals is a List of VisualObjects
   */
  void appendMultiple(Queue<VisualObject> visuals);

  /**
   * Removes a Visual Object to the Current Display
   * @param visual is a single VisualObject
   */
  void remove(VisualObject visual);

  /**
   * Removes multiple Visual Objects to the Current Display
   * @param visuals is a List of VisualObjects
   */
  void removeMultiple(List<VisualObject> visuals);

  /**
   * Adds a Queue of Visuals to the master que and triggers an update
   * @param visuals is a List of VisualObjects
   */
  void update(Queue<VisualObject> visuals);

  /**
   * Clears the Turtle View Screen
   */
  void clearScreen();

  /**
   * Saves the Turtle View Screen
   *
   */
  void saveScreen();

  /**
   * Adds a single command to the command History queue
   * @param command is a Visual Command from the Controller
   */
  void appendCommand(VisualCommand command);

  /**
   * Adds multiple commands to the command History Queue
   * @param commands is a List of Visual Commands from the Controller
   */
  void appendMultipleCommands(Queue<VisualCommand> commands);

  /**
   * Feel free to completely change this code or delete it entirely.
   */
  class Main {
      /**
       * Start of the program.
       */
      public static void main (String[] args) {
          System.out.println("Hello world");
      }
  }
}
