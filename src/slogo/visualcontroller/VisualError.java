package slogo.visualcontroller;

import javafx.scene.paint.Color;
import slogo.exceptions.LogicalException;

public class VisualError {

    private final String myMessage;
    private final ErrorSeverity mySeverity;
    private static final String SEPARATOR = " ERROR: ";

    public VisualError(LogicalException e) {
        this.myMessage = e.getMessage();
        this.mySeverity = e.getErrorSeverity();
        testErrorPrint();
    }

    private void testErrorPrint() {
        System.out.println("Created visual error with message: " + this.toString());
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
        return mySeverity.getLevel().toUpperCase() + SEPARATOR + this.myMessage;
    }

}
