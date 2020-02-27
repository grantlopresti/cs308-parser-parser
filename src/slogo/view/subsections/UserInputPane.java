package slogo.view.subsections;


import javafx.scene.control.TextArea;
import slogo.exceptions.InvalidCommandException;
import slogo.logicalcontroller.LogicalController;
import slogo.view.windows.SlogoView;
import slogo.visualcontroller.VisualError;

public class UserInputPane implements SubPane {
  private SlogoView myViewer;
  private LogicalController myLogicalController;
  private TextArea myTextArea;

  public UserInputPane(SlogoView viewer, LogicalController logicalcontroller) {
    myTextArea = new TextArea();
    myTextArea.setPromptText("Enter Logo Commands Here:");
    myLogicalController = logicalcontroller;
    myViewer = viewer;
  }

  public void setInputArea(String text){
    myTextArea.setText(text);
  }

  @Override
  public TextArea getNode() {
    return myTextArea;
  }

  public void sendUserCommand() {
    String userCommand = myTextArea.getText();
    try {
      if (!userCommand.equals("")) {
        myLogicalController.handleNewCommand(userCommand);
      }
    }
    catch (Exception e){
      myViewer.announceError(new VisualError(new InvalidCommandException("The following command "
          + "is invalid, please try another!\n" + userCommand)));
    }
  }

  public void clear() {
    myTextArea.clear();
  }
}

