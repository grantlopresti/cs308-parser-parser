package slogo.model;

import java.util.*;

/**
 * The purpose of this class is to provide an encapsulation of the data structure used to hold multiple
 * ModelObjects, so that the Logical Controller does not need to depend on any specific implementation
 * of data structure. Implements the Iterable interface.
 * @author Alex Xu
 */
public class ModelCollection implements Iterable {

    private List<ModelObject> myModelObjectList;
    private Map<Integer, ModelObject> myModelObjectMap;

    /**
     * Default Constructor for ModelCollection object
     */
    public ModelCollection(){
        initLists();
    }

    private void initLists() {
        this.myModelObjectList = new ArrayList<ModelObject>();
        this.myModelObjectMap = new HashMap<Integer, ModelObject>();
    }

    public ModelCollection(List<ModelObject> objects) {
        this();
        for (ModelObject o: objects) {
            this.append(o);
        }
    }

    /**
     * Add a ModelObject to the ModelCollection
     * @param object
     */
    public void append(ModelObject object){
        // System.out.println("appending turtle: ")
        myModelObjectList.add(object);
        myModelObjectMap.put(object.getID(), object);
    }

    /**
     * Get a ModelObject from the ModelCollection
     * @param index
     * @return
     */
    public ModelObject get(int index){
        return myModelObjectList.get(index);
    }

    public Map<Integer, ModelObject>  getModelMap() {
        return this.myModelObjectMap;
    }

    /**
     *
     * @return new model collection with all active turtles after a teller has been run
     */
    public ModelCollection getActiveTurtles() {
        List<ModelObject> activeTurtles = new ArrayList<ModelObject>();
        for (ModelObject o: this.myModelObjectList) {
            ModelTurtle turtle = (ModelTurtle) o;
            if (turtle.isActive()) {
                System.out.println("\nadding turtle: " + turtle.ID + " to active turtle collection");
                activeTurtles.add(turtle);
            }
        }
        return new ModelCollection(activeTurtles);
    }

    /**
     * Required by the Iterator interface
     * @return
     */
    @Override
    public Iterator iterator() {
        return myModelObjectList.iterator();
    }
}
