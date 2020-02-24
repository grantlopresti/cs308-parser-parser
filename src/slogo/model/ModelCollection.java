package slogo.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The purpose of this class is to provide an encapsulation of the data structure used to hold multiple
 * ModelObjects, so that the Logical Controller does not need to depend on any specific implementation
 * of data structure. Implements the Iterable interface.
 * @author Alex Xu
 */
public class ModelCollection implements Iterable {
    private List<ModelObject> modelObjectList;

    /**
     * Default Constructor for ModelCollection object
     */
    public ModelCollection(){
        modelObjectList = new ArrayList<ModelObject>();
    }

    /**
     * Add a ModelObject to the ModelCollection
     * @param myModelObject
     */
    public void append(ModelObject myModelObject){
        modelObjectList.add(myModelObject);
    }

    /**
     * Get a ModelObject from the ModelCollection
     * @param index
     * @return
     */
    public ModelObject get(int index){
        return modelObjectList.get(index);
    }

    /**
     * Required by the Iterator interface
     * @return
     */
    @Override
    public Iterator iterator() {
        return this.modelObjectList.iterator();
    }
}
