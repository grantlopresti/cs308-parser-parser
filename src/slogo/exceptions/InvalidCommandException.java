package slogo.exceptions;

import slogo.visualcontroller.ErrorSeverity;

/**
 * Exception is thrown when an Invalid Command is given.
 * @author ALex Xu
 */
public class InvalidCommandException extends LogicalException {

    public InvalidCommandException() {
        super();
        myErrorSeverity = ErrorSeverity.MEDIUM;
    }

    public InvalidCommandException(String message) {
        super(message);
        myErrorSeverity = ErrorSeverity.MEDIUM;
    }

    @Override
    public ErrorSeverity getErrorSeverity() {
        return myErrorSeverity;
    }

}
