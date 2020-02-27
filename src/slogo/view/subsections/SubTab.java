package slogo.view.subsections;

import javafx.beans.property.Property;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import slogo.view.windows.SlogoView;

public abstract class SubTab {

  protected SlogoView myViewer;
  protected VBox myVBox;
  protected ListView myListView;

  public SubTab(SlogoView viewer) {
    myVBox = new VBox();
    myListView = new ListView();
    myViewer = viewer;
  }

  public abstract Tab getTab(Property property);

  protected void setProperty(Property property) {
    myListView = new ListView();
    myListView.itemsProperty().bind(property);
    myListView.setPrefHeight(620);
    myListView.setOnMouseClicked(
        e -> myViewer.setUserInputAreaText(myListView.getSelectionModel().getSelectedItem().toString()));
    myVBox.getChildren().add(myListView);
  }

}
