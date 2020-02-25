package slogo.visualcontroller;

import javafx.scene.paint.Color;

public class VisualError {

    private final String myMessage;
    private final ErrorSeverity mySeverity;

    public VisualError(String message, ErrorSeverity severe) {
        this.myMessage = message;
        this.mySeverity = severe;
    }

    public ErrorSeverity getSeverity() {return this.mySeverity;}
    public Color getColor() {return this.mySeverity.getColor();}
    public String getMessage() {return this.myMessage;}

}
