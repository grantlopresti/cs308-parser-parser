package slogo.logicalcontroller.command;


public interface Command {

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
