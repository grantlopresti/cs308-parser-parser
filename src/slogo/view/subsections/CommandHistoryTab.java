package slogo.view.subsections;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import slogo.view.windows.SlogoView;
import slogo.visualcontroller.VisualCommand;

public class CommandHistoryTab implements SubTab {

  private SlogoView myViewer;

  private List<VisualCommand> myCommands;

  private VBox myVBox;

  public CommandHistoryTab(SlogoView viewer) {
    myViewer = viewer;
    myCommands = new ArrayList<>();
    myVBox = new VBox();
    addCommand(new VisualCommand("test"));
  }

  public void addCommand(VisualCommand command) {
    myCommands.add(command);
  }

  public void updateTab(){
    for (VisualCommand command: myCommands){
      myVBox.getChildren().add(getVisualizedCommand(command));
    }
  }

  private Node getVisualizedCommand(VisualCommand command) {
    Button clickableCommand = new Button(command.getString());
    clickableCommand.setOnAction(e -> myViewer.setUserInputAreaText(command.getString()));
    return clickableCommand;
  }

  @Override
  public Tab getTab() {
    updateTab();
    Tab tab = new Tab("Command History", myVBox);
    tab.getStyleClass().addAll("command-tab");
    return tab;
  }
}
