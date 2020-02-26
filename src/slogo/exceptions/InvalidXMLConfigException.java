package slogo.exceptions;

import slogo.visualcontroller.ErrorSeverity;

/**
 * Exception is thrown when there is an Invalid XML Configuration file, for configuring aesthetics.
 * @author Alex Xu
 */
public class InvalidXMLConfigException extends LogicalException {

    public InvalidXMLConfigException() {
        super();
        this.myErrorSeverity = ErrorSeverity.CRITICAL;
    }

    @Override
    public ErrorSeverity getErrorSeverity() {
        return this.myErrorSeverity;
    }

}
