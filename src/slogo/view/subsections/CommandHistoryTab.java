package slogo.view.subsections;

import javafx.scene.control.Tab;

public class CommandHistoryTab implements SubTab {

  @Override
  public Tab getTab() {
    return new Tab("Command History");
  }

}
