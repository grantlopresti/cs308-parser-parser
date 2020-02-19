package slogo.view.subpanes;

import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

public class VisualizationPane implements SubPane {

  @Override
  public GridPane getNode() {
    GridPane visualizer = new GridPane();

    visualizer.getChildren().add(new TextArea());

    return visualizer;
  }
}
