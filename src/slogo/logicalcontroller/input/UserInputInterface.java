package slogo.logicalcontroller.input;

import java.util.List;

public interface UserInputInterface {

    /**
     * Finds the next line that has more than one word
     * @return the next line that has a command
     */
    public int findNextLine();

    /**
     * Traverses the line at index to find the last command index
     * @param index of the proper line in user input
     * @returns the index of the last command
     */
    public int findLastCommand(int index);

    /**
     *
     * @param index of the line to fetch
     * @return the line that we want
     */
    public String getLine(int index);

    /**
     *
     * @param line is the line number
     * @param index is the index in the line to process
     * @param params is the parameter count to traverse
     * @returns the list of arguments to process
     */
    public List<String> getArguments(int line, int index, int params);

}
