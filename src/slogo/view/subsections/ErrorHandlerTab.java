package slogo.view.subsections;

import javafx.beans.property.Property;
import javafx.scene.control.Tab;

public class ErrorHandlerTab implements SubTab {

  @Override
  public Tab getTab(Property property) {
    setProperty(property);
    return new Tab("Error Handler");
  }

  private void setProperty(Property property) {

  }
}
