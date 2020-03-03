package slogo.view.subsections;

import javafx.beans.property.Property;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import slogo.view.windows.SlogoView;

public class SubTab {

  protected SlogoView myViewer;
  protected VBox myVBox;
  protected ListView myListView;

  private String myName;

  public SubTab(SlogoView viewer) {
    myVBox = new VBox();
    myListView = new ListView();
    myViewer = viewer;
  }

  protected void setProperty(Property property) {
    myListView = new ListView();
    myListView.itemsProperty().bind(property);
    myListView.setPrefHeight(620);
    myListView.setOnMouseClicked(
        e -> myViewer.setUserInputAreaText(myListView.getSelectionModel().getSelectedItem().toString()));
    myVBox.getChildren().add(myListView);
  }

  public void setName(String name){
    myName = name;
  }

}
