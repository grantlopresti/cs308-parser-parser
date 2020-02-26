package slogo.view.subsections;

import javafx.beans.property.Property;
import javafx.scene.control.Tab;

public class DefinedFunctionsTab implements SubTab {

  @Override
  public Tab getTab(Property property) {
    return new Tab("Defined Functions");
  }
}
