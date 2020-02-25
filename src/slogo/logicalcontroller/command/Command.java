package slogo.logicalcontroller.command;


public interface Command<E> {

    //FIXME: Please Use Generics to make command objects flexible in return value - Alex X.

    /**
     * Getter method to return the value of the command, also the number of units of movement.
     * @return
     */
    public double getValue();

    /**
     * Getter method to return the type of the Command object. Ex: fd, rt
     * @return
     */
    public String getCommandType();

    /**
     * Method to return the String representation of the Command object.
     * @return
     */
    public String toString();

}
