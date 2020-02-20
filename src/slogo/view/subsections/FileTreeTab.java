package slogo.view.subsections;

import javafx.scene.control.Tab;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class FileTreeTab implements SubTab{

  @Override
  public Tab getTab() {

    TreeItem<String> projectsTree = new TreeItem<>("Projects");
    projectsTree.getChildren().addAll(
        new TreeItem<>("Project 1"),
        new TreeItem<>("Project 2"),
        new TreeItem<>("Project 3"),
        new TreeItem<>("Project 4"));

    Tab projectsTab = new Tab("Project List");
    projectsTab.setContent(new TreeView<>(projectsTree));

    return projectsTab;
  }
}
