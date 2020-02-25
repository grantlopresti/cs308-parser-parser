package slogo.view;

import javafx.application.Platform;
import javafx.stage.Stage;
import slogo.logicalcontroller.LogicalController;
import slogo.view.windows.Home;
import slogo.view.windows.SlogoView;

import java.io.IOException;

public class ViewTester {

  private static final SlogoView mainStage = new SlogoView();
  //private static final Home mainStage = new Home();

  public static void main(String[] args) {

    LogicalController.initializeController();

    Platform.startup(() -> {
      try {
        mainStage.start(new Stage());
      } catch (IOException e) {
        e.printStackTrace();
      }
    });



  }
}
