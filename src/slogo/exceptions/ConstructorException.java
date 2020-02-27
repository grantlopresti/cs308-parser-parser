package slogo.exceptions;

import slogo.visualcontroller.ErrorSeverity;

public class ConstructorException extends LogicalException {

    private static final String message = "Constructor exception";

    public ConstructorException() {
        super();
        this.myErrorSeverity = ErrorSeverity.MEDIUM;
        this.myString = message;
    }

    public ConstructorException(String message) {
        super(message);
        this.myErrorSeverity = ErrorSeverity.MEDIUM;
    }
}
