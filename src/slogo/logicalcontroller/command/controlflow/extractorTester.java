package slogo.logicalcontroller.command.controlflow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class extractorTester implements ControlFlowExtractor {

    private static List<String> rawCmds1 = new ArrayList<>(Arrays.asList("dotimes [ :k quotient 360 :angle ]"));
    private static List<String> rawCmds4 = new ArrayList<>(Arrays.asList("repeat 9 [", "  repeat 180 [", "    fd 1 rt 2", "  ]", "  rt 40", "]"));
    private static List<String> rawCmds2 = new ArrayList<>(Arrays.asList("[", "  fd :distance", "  rt :angle", "  ifelse equal? quotient :k 2 0 [", "    pu", "  ] [", "    pd", "  ]", "]"));
    private static List<String> rawCmds3 = new ArrayList<>(Arrays.asList("repeat 5 [", "[", "  fd :distance", "  rt :angle", "  ifelse equal? quotient :k 2 0 [", "    pu", "  ] [", "    pd", "  ]", "]", "]"));


    public static List<String> extractorTester(){


        List<String> test = ControlFlowExtractor.initControlFlow(rawCmds3, 0, 2);

        System.out.println("Result: ");
        for(String elem: test){
            System.out.println(elem);
        }

        return test;


    }

    public static void main(String[] args){
        List<String> result = extractorTester();
    }
}
