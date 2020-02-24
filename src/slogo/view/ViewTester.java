package slogo.view;


import javafx.application.Platform;
import javafx.stage.Stage;
import slogo.view.windows.SlogoView;

public class ViewTester {

  private static final SlogoView mainStage = new SlogoView();
  //private static final SlogoView mainStage = new Home();

  public static void main(String[] args) {

    Platform.startup(() -> mainStage.start(new Stage()));

  }
}
