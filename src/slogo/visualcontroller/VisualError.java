package slogo.visualcontroller;

import javafx.scene.paint.Color;
import slogo.exceptions.LogicalException;

public class VisualError {

    private final String myMessage;
    private final ErrorSeverity mySeverity;
    private static final String SEPARATOR = ": ";

    public VisualError(LogicalException e) {
        myMessage = e.getMessage();
        mySeverity = e.getErrorSeverity();
    }

    public VisualError(String message, ErrorSeverity severe) {
        myMessage = message;
        mySeverity = severe;
    }

    public ErrorSeverity getSeverity() {return mySeverity;}
    public Color getColor() {return mySeverity.getColor();}

    @Override
    public String toString() {return errorMessage();}

    private String errorMessage() {
        return mySeverity.getLevel() + SEPARATOR + myMessage;
    }

}
