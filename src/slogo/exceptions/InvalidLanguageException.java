package slogo.exceptions;

import slogo.visualcontroller.ErrorSeverity;

public class InvalidLanguageException extends LogicalException {

  public InvalidLanguageException() {
    super();
    myErrorSeverity = ErrorSeverity.CRITICAL;
  }

  public InvalidLanguageException(String message) {
    super(message);
    myErrorSeverity = ErrorSeverity.CRITICAL;
  }

  @Override
  public ErrorSeverity getErrorSeverity() {
    return myErrorSeverity;
  }
}
