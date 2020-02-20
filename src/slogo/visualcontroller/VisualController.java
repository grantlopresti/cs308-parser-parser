package slogo.visualcontroller;

import java.io.File;
import java.util.ArrayList;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

public class VisualController {
  private ArrayList<VisualTurtle> myTurtles = new ArrayList<>();

  public VisualController(){

  }

  public static String loadFile() {
    FileChooser fc = new FileChooser();
    String dataPath = System.getProperty("user.dir") + "/data/examples";
    File workingDirectory = new File(dataPath);
    fc.setInitialDirectory(workingDirectory);

    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Logo files (*.logo)",
        "*.logo");
    fc.getExtensionFilters().add(extFilter);

    File file = fc.showOpenDialog(new Stage());
    if (file == null) {
      return null;
    }
    return file.toString();
  }
}
