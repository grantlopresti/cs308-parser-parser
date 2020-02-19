package slogo.controllers.logicalcontroller;

public interface Command {


    /**
     * Getter method to return the value of the command, also the number of units of movement.
     * @return
     */
    public double getValue();

    /**
     * Getter method to return the type of the Command object. Ex: fd, rt
     * @return
     */
    public double getCommandType();

    /**
     * Method to return the String representation of the Command object.
     * @return
     */
    public String toString();


}
