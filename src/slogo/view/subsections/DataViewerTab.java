package slogo.view.subsections;

import javafx.beans.property.Property;
import javafx.scene.control.Tab;

public class DataViewerTab extends SubTab {

  private static final String TAB_NAME = "Data/Variables";
  private static final String TAB_ELEMENTS = "data-tab";

  public DataViewerTab() {
    super();
  }

  @Override
  public Tab getTab(Property property) {
    setProperty(property);
    return new Tab(TAB_NAME);
  }

}
