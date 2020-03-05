package slogo.logicalcontroller.command.controlflow;

import java.util.*;

/**
 * Interface to extract information from the control flow commands
 */

public interface ControlFlowExtractor {

    static List<String> initControlFlow(List<String> rawCommands, int lineIndex, int bracIndex){

        int[] retIndexes = retEndIndex(rawCommands, lineIndex, bracIndex);
        int endLineIndex = retIndexes[0];
        int endBracIndex = retIndexes[1];
        List<String> body;
        if(lineIndex==endLineIndex){
            body = singleLineBody(bracIndex, endBracIndex, lineIndex, rawCommands);
        }
        else{
            body = getInternalContents(lineIndex, endLineIndex, rawCommands);
        }
        return body;

    }

    private static List<String> singleLineBody(int bracIndex, int endBracIndex, int lineIndex, List<String> rawCommands) {
        List<String> retLine = new ArrayList<String>();
        String line = rawCommands.get(lineIndex);
        String[] args = Arrays.copyOfRange(line.split("\\s+"), bracIndex+1, endBracIndex);
        retLine.add(String.join(" ", args));
        return retLine;
    }

    private static int[] retEndIndex(List<String> rawCommands, int lineIndex, int bracIndex){
        Stack<String> bracStack = new Stack<String>();

        for(int i = lineIndex; i<rawCommands.size(); i++){
            String line = rawCommands.get(i);
            String[] lineElems = line.split("\\s+");
            int eb = 0;
            for(int j = 0; j<lineElems.length; j++){
                if(lineElems[j].equals("[")){
                    bracStack.push("[");
                }else if(lineElems[j].equals("]")){
                    bracStack.pop();
                    eb = j;
                }
            }
            if(bracStack.isEmpty()){
                return new int[]{i, eb};
            }
        }

        return new int[0];

    }

    private static List<String> getInternalContents(int start, int end, List<String> rawCommands){
        List<String> retLines = new ArrayList<String>();
        for(int i = start+1; i<end; i++){
            retLines.add(rawCommands.get(i));
        }
        return retLines;
    }
}
