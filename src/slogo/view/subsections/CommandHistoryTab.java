package slogo.view.subsections;

import javafx.beans.property.Property;
import javafx.scene.control.Tab;

public class CommandHistoryTab implements SubTab {

  @Override
  public Tab getTab(Property property) {
    return new Tab("Command History");
  }

}
