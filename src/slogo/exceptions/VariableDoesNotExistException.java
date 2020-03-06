package slogo.exceptions;

import slogo.visualcontroller.ErrorSeverity;

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
