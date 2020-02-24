package slogo.logicalcontroller;

import slogo.exceptions.InvalidCommandException;
import slogo.logicalcontroller.parser.Parser;
import slogo.model.ModelCollection;
import slogo.model.ModelObject;
import slogo.visualcontroller.VisualCommand;

import java.lang.reflect.Method;

import java.util.*;
import java.io.*;

/**
 * Logical controller handles the interaction between the user input from the GUI, the parser, command objects,
 * variables, and changes in the Model package.
 * @author Alex Xu and Amjad S.
 */
public class LogicalController {

  private ModelCollection myModelCollection;
  private List<VisualCommand> myVisualCommands;
  private Parser myParser;

  private Stack<String> commands = new Stack<String>();
  private Stack<String> values = new Stack<String>();
  private File langFile = new File("./resources/");
  private File currentLangFile;
  private ResourceBundle langResources;
  private String language;
  private InputStream inputStream;
  private Map<String, String> commandArray;
  private ResourceBundle resources;

  /**
   * Default constructor for the Logical Controller
   */
  public LogicalController() {
    initializeController();
  }

  public LogicalController(String language) throws IOException {
    this();
    this.language = language;
    commandArray = new HashMap<String, String>();
    FileInputStream fis = new FileInputStream("resources/languages/"+this.language+".properties");
    resources = new PropertyResourceBundle(fis);
    genCommandArray();
    System.out.println(Arrays.asList(this.commandArray));
  }

  public void setLanguage(String langauge){

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
   * Assumption is that there is only one ModelObject (Turtle)
   * @param command
   * @throws InvalidCommandException
   */
  //TODO: Note to self:  Need to change the code below to non-static. Also worry about error handling later.
  public static void handleNewCommand(String command) throws InvalidCommandException {
    //TODO: Handle input command, try/catch for invalid and route potential error back to
    System.out.println(command);

    /*
    Command myCurrentCommand = myParser.parse(command);   //TODO: Amjad: Parser.parse() method needs to return a Command (or collection of commands, as implementation here can change easily)
                                                          //TODO: Naming of methods/classes can change and are flexible
    for (Object mo : myModelCollection){
      if(myCurrentCommand.getCommandCategory().equals(VISUAL_COMMAND_NAME){
        myVisualCommands.add(myCurrentCommand);
      }
      else{
        String commandName = myCurrentCommand.getCommandType();
        Method method = mo.getClass().getMethod(commandName, double.class);
        double myValue = myCurrentCommand.getValue();
        method.invoke(mo, myValue);
      }
    }
    */
  }

  /*
  /**
   * Returns collection of VisualCommands.
   * @return

  public List<VisualCommand> getVisualCommands(){
    return myVisualCommands;
  }
*/
  /**
   * Returns the collection of ModelObjects.
   * @return
   */
  //TODO: Not to self: change in the future so that it returns a collection of immutable modelobjects instead.
  public ModelCollection getModelCollection(){
    return myModelCollection;
  }

  /**
   * Initializes/Resets the Logical Controller.
   */
  private void initializeController(){
    myModelCollection = new ModelCollection();
    //myParser = new Parser();
    //myVisualCommands = new ArrayList<VisualCommand>();
  }

  /*
  public static void main (String[] args) throws IOException {
    LogicalController lc = new LogicalController("French");
    System.out.println(lc.getLang());
  }
   */
}
