package slogo.view.subsections;

import javafx.beans.property.Property;
import javafx.scene.control.Tab;

public class ErrorHandlerTab implements SubTab {

  @Override
  public Tab getTab() {
    return new Tab("Error Handler");
  }

  @Override
  public void setProperty(Property property) {

  }
}
