package slogo.view.subsections;

import javafx.beans.property.Property;
import javafx.scene.control.Tab;

public class DataViewerTab implements SubTab {

  public DataViewerTab() {;}

  @Override
  public Tab getTab() {
    return new Tab("Data/Variables");
  }

  @Override
  public void setProperty(Property property) {

  }

}
