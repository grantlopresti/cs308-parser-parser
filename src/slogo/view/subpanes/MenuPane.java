package slogo.view.subpanes;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;

public class MenuPane implements SubPane {

  @Override
  public MenuBar getPane() {
    MenuBar menuBar = new MenuBar();
    Menu fileMenu = new Menu("File");
    fileMenu.getItems().addAll(new MenuItem("New"),
        new SeparatorMenuItem(),
        new MenuItem("Open"),
        new MenuItem("Save"),
        new MenuItem("Save As..."),
        new SeparatorMenuItem(),
        new MenuItem("Exit"));
    Menu editMenu = new Menu("Edit");
    editMenu.getItems().addAll(new MenuItem("Undo"),
        new MenuItem("Redo"),
        new MenuItem("Cut"),
        new MenuItem("Copy"),
        new MenuItem("Paste"),
        new SeparatorMenuItem(),
        new MenuItem("Search/Replace"));
    Menu helpMenu = new Menu("Help");
    helpMenu.getItems().addAll(new MenuItem("Help Contents"),
        new SeparatorMenuItem(),
        new MenuItem("About..."));
    menuBar.getMenus().addAll(fileMenu, editMenu, helpMenu);
    return menuBar;
  }

}
