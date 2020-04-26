import javafx.application.Platform;
import javafx.stage.Stage;

import java.io.IOException;
import slogo.Manager;
import slogo.view.windows.Home;
import slogo.view.windows.SlogoView;

public class Main {

    /**
     * runs the entire slogo Program by creating a splash screen from which managers and GUIS anc
     * be created
     * @param args
     */
    public static void main (String[] args) {
        Platform.startup(() -> {
            try {
                Home myHome = new Home();
                myHome.start(new Stage());
            } catch (IOException e) {
                // TODO - handle no start
            }
        });
    }


}
