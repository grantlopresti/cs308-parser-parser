package slogo.view.subsections;

import javafx.beans.property.Property;
import javafx.scene.control.Tab;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class FileTreeTab extends SubTab{

  private static final String TAB_NAME = "Project List";
  private static final String TAB_ELEMENTS = "project-tab";

  public FileTreeTab() {
    super();
  }

  @Override
  public Tab getTab(Property property) {
    setProperty(property);
    TreeItem<String> projectsTree = new TreeItem<>("Projects");
    projectsTree.getChildren().addAll(
        new TreeItem<>("Project 1"),
        new TreeItem<>("Project 2"),
        new TreeItem<>("Project 3"),
        new TreeItem<>("Project 4"));

    Tab projectsTab = new Tab(TAB_NAME);
    projectsTab.setContent(new TreeView<>(projectsTree));

    return projectsTab;
  }

}
