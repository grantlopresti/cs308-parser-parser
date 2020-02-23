package slogo.view;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.swing.plaf.ViewportUI;
import slogo.view.windows.SlogoView;
import slogo.visualcontroller.VisualObject;
import slogo.visualcontroller.VisualTurtle;

public class ViewTester {

  public static void main(String[] args) throws Exception {

    //final Home mainStage = new Home(handler);
    final SlogoView mainStage = new SlogoView();
    mainStage.init();

    Platform.startup(() -> {
      Stage stage = new Stage();
      mainStage.start(stage);
    });

    List<VisualTurtle> visualTurtles = new ArrayList<>();
    visualTurtles.add(new VisualTurtle());
    VisualTurtle customTurtle = new VisualTurtle();
    customTurtle.setCenter(100, 100);
    customTurtle.setColor(Color.RED);
    customTurtle.setSize(50);
    visualTurtles.add(customTurtle);

    //mainStage.updateVisualTurtles(visualTurtles);

  }
}
