package slogo.logicalcontroller.codefilters;

import java.lang.reflect.InvocationTargetException;

//TODO: THIS IS A TESTER CLASS THAT IS NOT MEANT TO BE LEFT IN THE FINAL PROGRAM.
public class FilterTester {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        String test1 = "This part should be left in #This part should be taken out \n#This entire comment should not exist\n This line should be the second line";
        String test2 = "    Lots  of   trailing      spaces   ";
        //String result = MasterCodeFilterUtility.filter(test2);

        System.out.println("PROCESSED RESULT: ");
        //System.out.println(result);
    }
}
