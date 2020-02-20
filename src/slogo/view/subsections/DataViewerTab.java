package slogo.view.subsections;

import javafx.scene.control.Tab;

public class DataViewerTab implements SubTab{

  @Override
  public Tab getTab() {
    return new Tab("Data/Variables");
  }

}
