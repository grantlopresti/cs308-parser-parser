import javafx.application.Platform;
import javafx.stage.Stage;

import java.io.IOException;
import slogo.Manager;
import slogo.view.windows.Home;
import slogo.view.windows.SlogoView;

public class Main {

    public static void main (String[] args) throws IOException {
        Platform.startup(() -> {
            try {
                Home myHome = new Home();
                myHome.start(new Stage());
            } catch (IOException e) {
                ; // TODO - handle no start
            }
        });
    }


}
