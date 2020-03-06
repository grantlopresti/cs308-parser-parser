package slogo.exceptions;

import slogo.visualcontroller.ErrorSeverity;

public class NoCommandFoundException extends LogicalException {

    public NoCommandFoundException() {
        super();
        this.myErrorSeverity = ErrorSeverity.BASIC;
    }

    public NoCommandFoundException(String message) {
        super(message);
        this.myErrorSeverity = ErrorSeverity.BASIC;
    }

    @Override
    public ErrorSeverity getErrorSeverity() {
        return myErrorSeverity;
    }

}
