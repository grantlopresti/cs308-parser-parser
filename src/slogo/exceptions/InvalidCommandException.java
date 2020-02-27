package slogo.exceptions;

import slogo.visualcontroller.ErrorSeverity;

/**
 * Exception is thrown when an Invalid Command is given.
 * @author ALex Xu
 */
public class InvalidCommandException extends LogicalException {

    private static final String message = "Invalid command";

    public InvalidCommandException() {
        super();
        this.myErrorSeverity = ErrorSeverity.MEDIUM;
        this.myString = message;
    }

    public InvalidCommandException(String message) {
        super(message);
        this.myErrorSeverity = ErrorSeverity.MEDIUM;
    }

}
