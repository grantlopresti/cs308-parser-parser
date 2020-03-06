package slogo.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import javafx.util.Pair;
import slogo.exceptions.ResourceBundleException;
import slogo.logicalcontroller.BundleInterface;
import slogo.view.subsections.ListTab;
import slogo.view.windows.SlogoView;
import slogo.visualcontroller.VisualController;
import slogo.visualcontroller.VisualProperty;


/**
 * Class that encapsulates possible Bet options and how they are created/chosen so other classes,
 * especially the Game logic, does not need to be aware of the implementation details.
 *
 * @author Robert C. Duvall
 */
public class SubTabFactory {
  private static final String PACKAGE = SubTabFactory.class.getPackageName();
  // where to find resource data, note you can use java's package syntax
  public static final String REFLECTION_RESOURCES = "src/slogo/view/resources/possibleTabs.properties";
  // tabs GUI can make
  private List<String> myPossibleTabs;
  // tabs constructor data
  private List<Pair<String, String>> myTabData;


  /**
   * Create default factory
   */
  public SubTabFactory () {
    myPossibleTabs = new ArrayList<>();
    myTabData = new ArrayList<>();
    // initialize list contents from resource file data
    ResourceBundle reflectionResources;
    try {
      reflectionResources = BundleInterface.createResourceBundle(REFLECTION_RESOURCES);
    } catch (IOException e) {
      throw new ResourceBundleException();
    }
    for (String key : Collections.list(reflectionResources.getKeys())) {
      myPossibleTabs.add(key);
      // divide the data into separate pieces
      String[] data = reflectionResources.getString(key).split(",");
      myTabData.add(new Pair<>(data[0], data[1]));
    }
  }

  // make ListTab from given data
  public ListTab makeTab (SlogoView viewer, VisualController controller, String className) throws IllegalStateException {
    try {
      // get the tab's data from file
      Pair<String, String> data = myTabData.get(myPossibleTabs.indexOf(className));
      ListTab newTab = new ListTab(viewer, data.getKey());
      newTab.getStyleClass().addAll("list-tab");
      newTab.setContent(newTab.getMyVBox());
      newTab.setProperty(controller.getProperty(VisualProperty.valueOf(data.getValue())));

      return newTab;

      } catch (Exception e) {
      // something went wrong, let someone know so they can deal with it
      throw new IllegalStateException("ERROR: unable to create a specific tab", e);
    }
  }
}
