import javafx.application.Platform;
import javafx.stage.Stage;
import slogo.Manager;

import java.io.IOException;
import slogo.view.windows.Home;
import slogo.view.windows.SlogoInterface;

public class Main {

    public static void main (String[] args) throws IOException {
        Platform.startup(() -> {
            try {
                Home myHome = new Home();
                myHome.start(new Stage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
