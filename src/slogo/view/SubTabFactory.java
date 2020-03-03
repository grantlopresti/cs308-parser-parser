package slogo.view;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import javafx.util.Pair;
import slogo.view.subsections.ListTab;


/**
 * Class that encapsulates possible Bet options and how they are created/chosen so other classes,
 * especially the Game logic, does not need to be aware of the implementation details.
 *
 * @author Robert C. Duvall
 */
public class SubTabFactory {
  private static final String PACKAGE = SubTabFactory.class.getPackageName();
  // where to find resource data, note you can use java's package syntax
  public static final String REFLECTION_RESOURCES = PACKAGE + ".resources.possibleTabs";
  // bets player can make
  private List<String> myPossibleBets;
  // bets constructor data
  private List<Pair<String, Integer>> myBetData;


  /**
   * Create default factory
   */
  public SubTabFactory () {
    myPossibleBets = new ArrayList<>();
    myBetData = new ArrayList<>();
    // initialize list contents from resource file data
    ResourceBundle reflectionResources = ResourceBundle.getBundle(REFLECTION_RESOURCES);
    for (String key : Collections.list(reflectionResources.getKeys())) {
      myPossibleBets.add(key);
      // divide the data into separate pieces
      //String[] data = reflectionResources.getString(key).split(",");
      //myBetData.add(new Pair<>(data[0], Integer.parseInt(data[1])));
    }
  }

/*
  public SubTab promptForBet () {
    System.out.println("You can make one of the following types of bets:");
    for (int k = 0; k < myPossibleBets.size(); k += 1) {
      System.out.println(String.format("%d) %s", (k + 1), myPossibleBets.get(k)));
    }
    int whichBet = ConsoleReader.promptRange("Please make a choice", 1, myPossibleBets.size()) - 1;
    return makeBet(myPossibleBets.get(whichBet), myBetData.get(whichBet));
  }
*/


  // make concrete class from the given class name and initial data
  public ListTab makeTab (String className) throws IllegalStateException {
    try {
      Pair<String, Integer> data = myBetData.get(myPossibleBets.indexOf(className));
      // use reflection to get a Constructor to call
      Class<?> clazz = Class.forName(PACKAGE + "." + className);
      Constructor<?> ctor = clazz.getDeclaredConstructor(String.class, Integer.TYPE);
      // use constructor and data to create an instance
      return (ListTab)ctor.newInstance(data.getKey(), data.getValue());
      // OR:
      //return (Bet)Class.forName(className).getDeclaredConstructor(String.class, Integer.TYPE).newInstance(data.getKey(), data.getValue());
    } catch (Exception e) {
      // something went wrong, let someone know so they can deal with it
      throw new IllegalStateException("ERROR: unable to create proper bet", e);
    }
  }
}
