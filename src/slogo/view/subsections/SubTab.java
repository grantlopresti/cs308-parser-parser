package slogo.view.subsections;

import javafx.beans.property.Property;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;

public abstract class SubTab {

  protected VBox myVBox;
  protected ListView myListView;

  public SubTab() {
    myVBox = new VBox();
    myListView = new ListView();
  }

  public abstract Tab getTab(Property property);

  protected void setProperty(Property property) {
    myListView = new ListView();
    myListView.itemsProperty().bind(property);
    myListView.setPrefHeight(620);
    myVBox.getChildren().add(myListView);
  }

}
