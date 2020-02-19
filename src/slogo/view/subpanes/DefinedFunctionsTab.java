package slogo.view.subpanes;

import javafx.scene.control.Tab;

public class DefinedFunctionsTab implements SubTab {

  @Override
  public Tab getTab() {
    return new Tab("Defined Functions");
  }
}
