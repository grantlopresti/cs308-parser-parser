package slogo.view.subpanes;

import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;

public class ToolbarPane implements SubPane {

  @Override
  public ToolBar getPane() {
    return new ToolBar(new Button("New"),
        new Button("Open"),
        new Separator(),
        new Button("Cut"),
        new Button("Copy"),
        new Button("Paste"));
  }

}
