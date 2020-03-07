import javafx.application.Platform;
import javafx.stage.Stage;

import java.io.IOException;
import slogo.Manager;
import slogo.view.windows.Home;
import slogo.view.windows.SlogoView;

public class Main {

    public static void main (String[] args) throws IOException {
        // startWithSplashScreen();
        startWithoutSplashScreen();
    }

    private static void startWithoutSplashScreen() throws IOException {
        Platform.startup(() -> {
            try {
                new Manager("ENGLISH");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private static void startWithSplashScreen() {
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
