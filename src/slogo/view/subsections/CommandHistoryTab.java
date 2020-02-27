package slogo.view.subsections;

import javafx.beans.property.Property;
import javafx.scene.control.Tab;
import slogo.view.windows.SlogoView;

public class CommandHistoryTab extends SubTab {

  private SlogoView myViewer;
  private static final String TAB_NAME = "Command History";
  private static final String TAB_ELEMENTS = "command-tab";

  public CommandHistoryTab(SlogoView viewer) {
    super(viewer);
  }

  public void setSlogoView(SlogoView viewer) {
    myViewer = viewer;
  }

  public Tab getTab(Property property) {
    setProperty(property);
    Tab tab = new Tab(TAB_NAME, myVBox);
    tab.getStyleClass().addAll(TAB_ELEMENTS);
    return tab;
  }





}
