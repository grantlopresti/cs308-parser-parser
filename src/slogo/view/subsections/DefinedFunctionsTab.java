package slogo.view.subsections;

import javafx.beans.property.Property;
import javafx.scene.control.Tab;
import slogo.view.windows.SlogoView;

public class DefinedFunctionsTab extends SubTab {

  private static final String TAB_NAME = "Defined Functions";
  private static final String TAB_ELEMENTS = "functions-tab";

  public DefinedFunctionsTab(SlogoView viewer) {
    super(viewer);
  }

  @Override
  public Tab getTab(Property property) {
    setProperty(property);
    Tab tab = new Tab(TAB_NAME, myVBox);
    // tab.getStyleClass().addAll(TAB_ELEMENTS);
    return tab;
  }

}
