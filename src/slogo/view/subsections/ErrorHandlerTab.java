package slogo.view.subsections;

import javafx.beans.property.Property;
import javafx.scene.control.Tab;


public class ErrorHandlerTab extends SubTab {

  private static final String TAB_NAME = "Error Handlers";
  private static final String TAB_ELEMENTS = "error-tab";

  public ErrorHandlerTab() {
    super();
  }

  @Override
  public Tab getTab(Property property) {
    setProperty(property);
    return new Tab(TAB_NAME);
  }

}
