package slogo.visualcontroller;

import javafx.scene.paint.Color;
import slogo.exceptions.LogicalException;

public class VisualError {

    private final String myMessage;
    private final ErrorSeverity mySeverity;
    private static final String SEPARATOR = ": ";

    public VisualError(LogicalException e) {
        this.myMessage = e.toString();
        this.mySeverity = e.getErrorSeverity();
    }

    public VisualError(String message, ErrorSeverity severe) {
        this.myMessage = message;
        this.mySeverity = severe;
    }

    public ErrorSeverity getSeverity() {return this.mySeverity;}
    public Color getColor() {return this.mySeverity.getColor();}

    @Override
    public String toString() {return errorMessage();}

    private String errorMessage() {
        return this.mySeverity.getLevel() + SEPARATOR + this.myMessage;
    }

}
