package slogo.view.subpanes;

import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;

public class ToolbarPane implements SubPane {

  @Override
  public ToolBar getNode() {
    return new ToolBar(new Button("New"),
        new Button("Load File"),
        new Button("Load & Run"),
        new Separator(),
        new Button("Background Color"),
        new Button("Turtle Image"),
        new Button("Pen Color"),
        new Button("Clear Screen"),
        new Separator(),
        new Button("Language"),
        new Separator(),
        new Button("Help/Info"));
  }

}
