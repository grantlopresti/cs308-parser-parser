package slogo.view.subsections;


import javafx.scene.control.TextArea;
import slogo.logicalcontroller.LogicalController;

public class UserInputPane implements SubPane {
  private LogicalController myLogicalController;
  private TextArea myTextArea;

  public UserInputPane(LogicalController logicalcontroller) {
    myTextArea = new TextArea();
    myTextArea.setPromptText("Enter Logo Commands Here:");
    myLogicalController = logicalcontroller;
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
    System.out.println(userCommand);
    try {
      if (!userCommand.equals("")) {
        myLogicalController.handleNewCommand(userCommand);
      }
    }
    catch (Exception e){
      e.printStackTrace();
    }
  }
}

