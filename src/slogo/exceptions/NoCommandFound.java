package slogo.exceptions;

import slogo.visualcontroller.ErrorSeverity;

public class NoCommandFound extends LogicalException {

    public NoCommandFound() {
        super();
        this.myErrorSeverity = ErrorSeverity.BASIC;
    }

    public NoCommandFound(String message) {
        super(message);
        this.myErrorSeverity = ErrorSeverity.BASIC;
    }

    @Override
    public ErrorSeverity getErrorSeverity() {
        return myErrorSeverity;
    }

}
