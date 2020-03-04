package slogo.exceptions;

import slogo.visualcontroller.ErrorSeverity;

public class ResourceBundleCreationException extends LogicalException {

    private static final String message = "Unable to find resource bundle";

    public ResourceBundleCreationException() {
        super();
        this.myErrorSeverity = ErrorSeverity.MEDIUM;
        this.myString = message;
    }

    public ResourceBundleCreationException(String message) {
        super(message);
        this.myErrorSeverity = ErrorSeverity.MEDIUM;
    }
}
