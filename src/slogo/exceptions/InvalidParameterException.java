package slogo.exceptions;

import slogo.visualcontroller.ErrorSeverity;

/**
 * Exception that is thrown when the user inputs an invalid Parameter.
 * @author Alex Xu
 */
public class InvalidParameterException extends LogicalException{
    private static final String DEFAULT_MESSAGE = "Invalid parameter";

    public InvalidParameterException() {
        super();
        this.myErrorSeverity = ErrorSeverity.MEDIUM;
        this.myString = DEFAULT_MESSAGE;
    }

    public InvalidParameterException(String message) {
        super(message);
        this.myString = message;
        this.myErrorSeverity = ErrorSeverity.MEDIUM;
    }
}
