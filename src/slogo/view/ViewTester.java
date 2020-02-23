package slogo.view;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import slogo.view.windows.SlogoView;
import slogo.visualcontroller.VisualTurtle;

public class ViewTester {

  private static final SlogoView mainStage = new SlogoView();
  //private static final SlogoView mainStage = new Home();

  public static void main(String[] args) throws Exception {

    Platform.startup(() -> mainStage.start(new Stage()));

  }
}
