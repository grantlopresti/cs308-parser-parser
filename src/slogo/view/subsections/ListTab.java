package slogo.view.subsections;

import javafx.beans.property.Property;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;
import slogo.view.windows.SlogoView;

public class ListTab extends Tab{

  protected SlogoView myViewer;
  protected VBox myVBox;
  protected ListView<String> myListView;

  public ListTab(SlogoView viewer, String tabName) {
    super(tabName);
    myVBox = new VBox();
    myListView = new ListView<>();
    myViewer = viewer;
  }

  public void setProperty(Property property) {
    myListView = new ListView<>();
    myListView.itemsProperty().bind(property);
    myListView.setPrefHeight(620);
    myListView.setOnMouseClicked(
        e -> setUserTextArea());
    myVBox.getChildren().add(myListView);
  }

  private void setUserTextArea() {
    Object clickedItem = myListView.getSelectionModel().getSelectedItem();
    if (clickedItem != null) {
      myViewer.setUserInputAreaText(clickedItem.toString());
    }
  }

  public VBox getMyVBox(){
    return myVBox;
  }

}
