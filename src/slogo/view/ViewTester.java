package slogo.view;


import java.io.IOException;
import javafx.application.Platform;
import javafx.stage.Stage;
import slogo.view.windows.Home;
import slogo.view.windows.SlogoView;

public class ViewTester {

  private static final SlogoView mainStage = new SlogoView();
  //private static final Home mainStage = new Home();

  public static void main(String[] args) {

    Platform.startup(() -> {
      mainStage.start(new Stage());
    });

  }
}
