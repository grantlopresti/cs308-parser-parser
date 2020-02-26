package slogo.view.subsections;

import javafx.beans.property.Property;
import javafx.scene.control.Tab;

public class DataViewerTab implements SubTab {

  public DataViewerTab() {;}

  @Override
  public Tab getTab(Property property) {
    setProperty(property);
    return new Tab("Data/Variables");
  }

  private void setProperty(Property property) {

  }

}
