package slogo.view.subsections;

import javafx.beans.property.Property;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;
import slogo.view.windows.SlogoView;

public class ListTab extends Tab{

  protected SlogoView myViewer;
  protected VBox myVBox;
  protected ListView myListView;

  public ListTab(SlogoView viewer, String tabName) {
    super(tabName);
    myVBox = new VBox();
    myListView = new ListView();
    myViewer = viewer;
  }

  public void setProperty(Property property) {
    myListView = new ListView();
    myListView.itemsProperty().bind(property);
    myListView.setPrefHeight(620);
    myListView.setOnMouseClicked(
        e -> myViewer.setUserInputAreaText(myListView.getSelectionModel().getSelectedItem().toString()));
    myVBox.getChildren().add(myListView);
  }

  public VBox getMyVBox(){
    return myVBox;
  }

}
