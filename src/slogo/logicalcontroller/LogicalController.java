package slogo.logicalcontroller;

import slogo.exceptions.InvalidCommandException;
import java.util.*;
import java.io.*;

public class LogicalController {

  private Stack<String> commands = new Stack<String>();
  private Stack<String> values = new Stack<String>();
  private File langFile = new File("./resources/");
  private File currentLangFile;
  private ResourceBundle langResources;
  private String language;
  private InputStream inputStream;
  private Map<String, String> commandArray;
  private ResourceBundle resources;





  public LogicalController(String language) throws IOException {
    this.language = language;
    commandArray = new HashMap<String, String>();
    FileInputStream fis = new FileInputStream("resources/languages/"+this.language+".properties");
    resources = new PropertyResourceBundle(fis);
    genCommandArray();
    System.out.println(Arrays.asList(this.commandArray));



  }

  public String getLang(){
    return this.language;
  }

  public void genCommandArray(){
    for(String key: Collections.list(resources.getKeys())){
      String regex = resources.getString(key);
      if(regex.indexOf("|") != -1){
        commandArray.put(regex.substring(0, regex.indexOf("|")), key);
        commandArray.put(regex.substring(regex.indexOf("|")+1), key);
      }
      else{
        commandArray.put(regex, key);
      }

    }
  }

  /**
   * Code that interacts with the GUI, and receives strings as commands
   * @param command
   * @throws InvalidCommandException
   */
  //TODO: May need to discuss the code below.
  public static void handleNewCommand(String command) throws InvalidCommandException {
    //TODO: Handle input command, try/catch for invalid and route potential error back to
    System.out.println(command);

    //Command myCurrentCommand = myParser.parse(command);

    //Then, get the command type and use reflection to call methods in on the List of ModelObjects.
    

  }

public static void main (String[] args) throws IOException {
  LogicalController lc = new LogicalController("French");
  System.out.println(lc.getLang());
}

}
