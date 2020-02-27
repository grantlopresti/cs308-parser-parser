package slogo.exceptions;

import slogo.visualcontroller.ErrorSeverity;

public class InvalidCommandFileException extends LogicalException {

  public InvalidCommandFileException() {
    super();
    myErrorSeverity = ErrorSeverity.MEDIUM;
  }

  public InvalidCommandFileException(String message) {
    super(message);
    myErrorSeverity = ErrorSeverity.MEDIUM;
  }

  @Override
  public ErrorSeverity getErrorSeverity() {
    return myErrorSeverity;
  }
}
