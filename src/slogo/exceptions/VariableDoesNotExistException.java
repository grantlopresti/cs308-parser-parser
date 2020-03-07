package slogo.exceptions;

import slogo.visualcontroller.ErrorSeverity;

/**
 * Exception thrown when the Variable requested does not exist
 * @author  Alex Xu
 */
public class VariableDoesNotExistException extends LogicalException{

    public VariableDoesNotExistException() {
        super();
        this.myErrorSeverity = ErrorSeverity.BASIC;
    }

    public VariableDoesNotExistException(String message) {
        super(message);
        this.myErrorSeverity = ErrorSeverity.BASIC;
    }
}
