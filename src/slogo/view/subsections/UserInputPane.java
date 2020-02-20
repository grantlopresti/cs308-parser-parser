package slogo.view.subsections;

import javafx.scene.control.TextArea;

public class UserInputPane implements SubPane {

  @Override
  public TextArea getNode() {
    TextArea inputField = new TextArea();
    return inputField;
  }
}
