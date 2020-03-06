package slogo.exceptions;

import slogo.visualcontroller.ErrorSeverity;

public class InvalidLanguageException extends LogicalException {

  private static final String MESSAGE = "Invalid language";

  public InvalidLanguageException() {
    super();
    this.myErrorSeverity = ErrorSeverity.CRITICAL;
    this.myString = MESSAGE;
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
