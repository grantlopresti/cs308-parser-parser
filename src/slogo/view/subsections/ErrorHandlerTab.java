package slogo.view.subsections;

import javafx.beans.property.Property;
import javafx.scene.control.Tab;
import slogo.view.windows.SlogoView;
import slogo.visualcontroller.VisualError;


public class ErrorHandlerTab extends SubTab {

  private static final String TAB_NAME = "Error Handlers";
  private static final String TAB_ELEMENTS = "error-tab";

  public ErrorHandlerTab(SlogoView viewer) {
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
