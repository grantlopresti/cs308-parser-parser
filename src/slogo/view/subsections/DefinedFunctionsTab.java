package slogo.view.subsections;

import javafx.beans.property.Property;
import javafx.scene.control.Tab;

public class DefinedFunctionsTab extends SubTab {

  private static final String TAB_NAME = "Defined Functions";
  private static final String TAB_ELEMENTS = "functions-tab";

  public DefinedFunctionsTab() {
    super();
  }

  @Override
  public Tab getTab(Property property) {
    setProperty(property);
    return new Tab(TAB_NAME);
  }

}
