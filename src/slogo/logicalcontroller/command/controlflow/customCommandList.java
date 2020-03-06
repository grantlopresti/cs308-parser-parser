package slogo.logicalcontroller.command.controlflow;

import slogo.logicalcontroller.variable.Variable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class customCommandList implements Iterable {
    private List<To> myCustomCommandList;

    public customCommandList(){
        this.myCustomCommandList = new ArrayList<To>();
    }

    public To get(int index){
        return myCustomCommandList.get(index);
    }

    public void addTo(To to){
        deleteTo(to.getName());
        myCustomCommandList.add(to);
    }

    public void deleteTo(int index){
        myCustomCommandList.remove(index);
    }

    public void deleteTo(String name){
        for(To to : myCustomCommandList){
            if(isSameVariable(name, to)){
                myCustomCommandList.remove(to);
            }
        }
    }

    public void deleteAll(){
        myCustomCommandList = new ArrayList<>();
    }

    public boolean isSameVariable(String toName, To to){
        return toName.equals(to.getName());
    }

    public boolean isEmpty() {
        return this.myCustomCommandList.size() == 0;
    }

    @Override
    public Iterator iterator() {
        return myCustomCommandList.iterator();
    }
}
