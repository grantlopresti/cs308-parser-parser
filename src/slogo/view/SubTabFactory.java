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
 * Class that encapsulates possible SubTab options and how they are created/chosen so other classes,
 * especially the SlogoView class, does not need to be aware of the implementation details.
 *
 * @author Grant LoPresti
 */
public class SubTabFactory {
  // where to find resource data, note you can use java's package syntax
  public static final String REFLECTION_RESOURCES = "src/slogo/view/resources/possibleTabs.properties";
  // tabs GUI can make
  private List<String> myPossibleTabs;
  // tabs constructor data
  private List<Pair<String, String>> myTabData;


  /**
   * Create default factory
   *
   * @author Grant LoPresti
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

  /**
   * Makes a list tab from the given data
   * @param viewer is the SlogoView that it communicates with
   * @param controller is the Visual Controller which the listview is bound to
   * @param className tells the method exactly what kind of tab to make
   * @return the new List Tab
   * @throws IllegalStateException if unable to create new tab
   *
   * @author Grant LoPresti
   */
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
