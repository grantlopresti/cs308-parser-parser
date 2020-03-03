package slogo.view;


import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import slogo.view.subsections.SubTab;


/**
 * Class that encapsulates possible SubTab options and how they are created/chosen so other classes,
 * especially the Game logic, does not need to be aware of the implementation details.
 *
 * @author Grant LoPresti
 */
public class SubTabFactory {

  private ResourceBundle myBetTypeResources;
  private static final String tabResourcePath = "src.properties.tabs";
  private List<String> POSSIBLE_TABS;

  public SubTabFactory(){
    myBetTypeResources = ResourceBundle.getBundle(tabResourcePath);
    POSSIBLE_TABS = Collections.list(myBetTypeResources.getKeys());
  }

  private SubTab makeTab(String TabSubClass)
      throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
    String className = "game.roulette.bets." + TabSubClass;
    Class clazz = Class.forName(className);
    //Object bet = clazz.getConstructor().newInstance(myBetTypeResources.getString(TabSubClass).split(","));
    Object bet = clazz.getConstructor().newInstance();
    return (SubTab) bet;
  }

  /**
   * Prompt the user to make a bet from a menu of choices.
   */
  public SubTab promptForTab (String tabType) {
    int whichTab = POSSIBLE_TABS.indexOf(tabType);
    try {
      return makeTab(POSSIBLE_TABS.get(whichTab));
    } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
      //FIXME: Yes, we know this is dumb
      e.printStackTrace();
      return null;
    }
  }
}
