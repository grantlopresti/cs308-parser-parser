package slogo.view.subsections;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.Property;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import slogo.visualcontroller.VisualCommand;

public class CommandHistoryTab implements SubTab {

  private List<VisualCommand> myCommands;
  private VBox myVBox;
  private ListView myListView;

  public CommandHistoryTab() {
    myCommands = new ArrayList<>();
    myVBox = new VBox();
  }

  public void addCommand(VisualCommand command) {
    myCommands.add(command);
  }

  // NOTE - these two methods no longer needed with bindigns, automatic updates after property set
  public void updateTab(){
    for (VisualCommand command: myCommands){
      myVBox.getChildren().add(getVisualizedCommand(command));
    }
  }

  private Text getVisualizedCommand(VisualCommand command) {
    return new Text(command.getString());
  }

  @Override
  public Tab getTab(Property property) {
    updateTab();
    setProperty(property);
    return new Tab("Command History", myVBox);
  }

  // NOTE - this is the magic sauce, when the itemsproperty is bound as visualcontroller instance changes, yours does too
  private void setProperty(Property property) {
    myListView = new ListView();
    myListView.itemsProperty().bind(property);
    myVBox.getChildren().add(myListView);
  }
}
