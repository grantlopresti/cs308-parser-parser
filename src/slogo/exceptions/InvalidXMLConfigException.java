package slogo.exceptions;

import slogo.visualcontroller.ErrorSeverity;

/**
 * Exception is thrown when there is an Invalid XML Configuration file, for configuring aesthetics.
 * @author Alex Xu
 */
public class InvalidXMLConfigException extends LogicalException {

    private static final String message = "Could not configure XML";

    public InvalidXMLConfigException() {
        super();
        this.myErrorSeverity = ErrorSeverity.CRITICAL;
        this.myString = message;
    }

    @Override
    public ErrorSeverity getErrorSeverity() {
        return this.myErrorSeverity;
    }

}
