package slogo.exceptions;

import slogo.visualcontroller.ErrorSeverity;

/**
 * Exception thrown when the Filters are not set up properly
 * @author Alex Xu
 */
public class InvalidFilterException extends LogicalException {

    private static final String DEFAULT_MESSAGE = "Invalid Filter Configuration";

    public InvalidFilterException() {
        super();
        this.myErrorSeverity = ErrorSeverity.MEDIUM;
        this.myString = DEFAULT_MESSAGE;
    }

    public InvalidFilterException(String message) {
        super(message);
        this.myString = message;
        this.myErrorSeverity = ErrorSeverity.MEDIUM;
    }
}
