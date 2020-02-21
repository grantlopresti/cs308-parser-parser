package slogo.view.subsections;


import javafx.scene.control.TextArea;
import slogo.logicalcontroller.LogicalController;

public class UserInputPane implements SubPane {

  private TextArea myTextArea;

  public UserInputPane() {
    myTextArea = new TextArea();
    myTextArea.setPromptText("Enter Logo Commands Here:");
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
    myTextArea.clear();
    try {
      LogicalController.handleNewCommand(userCommand);
    }
    catch (Exception e){
      e.printStackTrace();
    }
  }
}

