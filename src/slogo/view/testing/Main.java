package slogo.view.testing;

import java.awt.Dimension;
import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.shape.Path;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.HLineTo;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.paint.Color;
import javafx.util.Duration;

//testing

/**
 * An example class to show how to animate objects over time.
 *
 * Disclaimer: The code here for the GUI is poorly written (everything is done in this class and
 * hardcoded), and is merely to demonstrate the function calls for sequencing animations.
 *
 * @author Robert C. Duvall
 */
public class Main extends Application {
  public static final String TITLE = "JavaFX Animation Example";
  public static final Dimension DEFAULT_SIZE = new Dimension(400, 100);

  private Node myActor;

  @Override
  public void start (Stage primaryStage) {
    primaryStage.setTitle(TITLE);
    primaryStage.setScene(makeScene(DEFAULT_SIZE.width, DEFAULT_SIZE.height));
    primaryStage.show();

    // create and start animation, could be used in separate contexts
    Animation animation = makeAnimation(myActor);
    animation.play();
  }

  // create a simple scene
  private Scene makeScene (int width, int height) {
    Group root = new Group();
    myActor = makeActor();
    root.getChildren().add(myActor);
    return new Scene(root, width,  height);
  }

  // create something to animate
  private Node makeActor () {
    Shape result = new Rectangle(50, 50, 50, 50);
    result.setFill(Color.PLUM);
    return result;
  }

  // create sequence of animations
  private Animation makeAnimation (Node agent) {
    // create something to follow
    Path path = new Path();
    path.getElements().addAll(new MoveTo(50, 50), new HLineTo(350));
    // create an animation where the shape follows a path
    PathTransition pt = new PathTransition(Duration.seconds(4), path, agent);
    // create an animation that rotates the shape
    RotateTransition rt = new RotateTransition(Duration.seconds(3));
    rt.setByAngle(90);
    // put them together in order
    return new SequentialTransition(agent, pt, rt);
  }

  /**
   * Start of the program --- hand control off to JavaFX.
   */
  public static void main (String[] args) {
    Application.launch(args);
  }
}
