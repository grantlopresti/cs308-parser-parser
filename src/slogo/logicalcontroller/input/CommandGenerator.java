package slogo.logicalcontroller.input;

import slogo.exceptions.InvalidCommandException;
import slogo.logicalcontroller.command.Command;

import java.lang.reflect.Constructor;
import java.util.List;

public interface CommandGenerator {

    static Command createControlCommand(String superclass, String command, List<List<String>> args) {
        try {
            Class clazz = Class.forName(createCommandPath(superclass, command));
            Constructor ctor = clazz.getConstructor(List.class);
            return (Command) ctor.newInstance(args);
        } catch (Exception e) {
            throw new InvalidCommandException("Could not create control flow command");
        }
    }

    static String createCommandPath(String superclass, String command) {
        final String SLOGO_COMMAND = "slogo.logicalcontroller.command.";
        String path = String.format("%s%s.%s", SLOGO_COMMAND, superclass, command);
        System.out.printf("returning path: %s \n", path);
        return path;
    }

    static Command createCommand(String superclass, String command, List<String> arguments) {
        try {
            Class clazz = Class.forName(createCommandPath(superclass, command));
            Constructor ctor = clazz.getConstructor(List.class);
            return (Command) ctor.newInstance(arguments);
        } catch (Exception e) {
            e.printStackTrace();
            throw new InvalidCommandException("Could not create command");
        }
    }

}
