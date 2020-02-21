package slogo.view;

import javafx.application.Platform;
import javafx.stage.Stage;
import slogo.view.windows.SlogoView;

public class ViewTester {

  public static void main(String[] args) throws Exception {

    //final Home mainStage = new Home(handler);
    final SlogoView mainStage = new SlogoView();
    mainStage.init();

    Platform.startup(() -> {
      Stage stage = new Stage();
      mainStage.start(stage);
    });
  }

}
