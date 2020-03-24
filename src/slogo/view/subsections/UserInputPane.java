package slogo.view.subsections;

import javafx.scene.control.TextArea;

public class UserInputPane {
  private TextArea myTextArea;

  /**
   * Constructor that builds an input pane containing a manipulable text area
   *
   * @author Grant LoPresti
   */
  public UserInputPane () {
    myTextArea = new TextArea();
    myTextArea.setPromptText("Enter Logo Commands Here:");
  }

  /**
   * sets the text of the user input area, useful when putting commands into the box to be run
   * @param text is the new content of the textbox
   *
   * @author Grant LoPresti
   */
  public void setInputArea(String text){
    myTextArea.setText(text);
  }

  /**
   * returns the text area to be visualized by the SlogoView
   * @return the textbox form of itself
   *
   * @author Grant LoPresti
   */
  public TextArea getNode() {
    return myTextArea;
  }

  /**
   * clears textbox
   *
   * @author Grant LoPresti
   */
  public void clear() {
    myTextArea.clear();
  }

  /**
   * gets the user typed or auto inputted command from the textbox
   * @return the text typed into the box
   */
  public String getCommand() {
    return myTextArea.getText();
  }
}

