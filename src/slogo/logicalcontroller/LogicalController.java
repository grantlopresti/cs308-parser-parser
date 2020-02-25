package slogo.logicalcontroller;

import slogo.exceptions.InvalidCommandException;
import slogo.model.ModelCollection;
import slogo.model.ModelTurtle;

/**
 * Logical controller handles the interaction between the user input from the GUI, the parser, command objects,
 * variables, and changes in the Model package.
 * @author Alex Xu and Amjad S.
 */
public class LogicalController {
  private ModelCollection myModelCollection;
  private Parser myParser;

  private LogicalController() {}

  public static void setLanguage(String langauge){
    myParser = new Parser();
  }


  /*
  public LogicalController(String language) throws IOException {
    this();
    this.language = language;
    commandArray = new HashMap<String, String>();
    FileInputStream fis = new FileInputStream("resources/languages/"+this.language+".properties");
    resources = new PropertyResourceBundle(fis);
    genCommandArray();
    System.out.println(Arrays.asList(this.commandArray));
  }
*/







  /**
   * Code that interacts with the GUI, and receives strings as commands
   * Assumption is that there is only one ModelObject (Turtle)
   * @param command
   * @throws InvalidCommandException
   */
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
  public void initializeController(){
    myModelCollection = new ModelCollection();
    myModelCollection.append(new ModelTurtle());
  }

  public static void main(String[] args){

  }

}
