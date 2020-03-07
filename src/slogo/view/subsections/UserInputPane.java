package slogo.view.subsections;

import javafx.scene.control.TextArea;

public class UserInputPane {
  private TextArea myTextArea;

  public UserInputPane () {
    myTextArea = new TextArea();
    myTextArea.setPromptText("Enter Logo Commands Here:");
  }

  public void setInputArea(String text){
    myTextArea.setText(text);
  }

  public TextArea getNode() {
    return myTextArea;
  }

  public void clear() {
    myTextArea.clear();
  }

  public String getCommand() {
    return myTextArea.getText();
  }
}

