package ch.supsi.fsci.client.controller;

import ch.supsi.fsci.engine.Commands.CommandInfo;
import ch.supsi.fsci.engine.Commands.CommandInterface;
import ch.supsi.fsci.engine.Exceptions.WrongCommandArgumentNumber;
import ch.supsi.fsci.engine.Exceptions.WrongCommandName;
import ch.supsi.fsci.engine.Model.FileSystemModel;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

import org.reflections.Reflections;

public class CommandExecutionController {
    private final HashMap<String, Class<? extends CommandInterface>> commandList;
    private final FileSystemModel fileSystemModel;

    /**
     * @brief Initializes the command executor, which takes care of executing the correct commands automatically.
     * @param fileSystemModel: the file system which will be used to execute the command methods.
     * @param packageToScan: the package that must be scanned for command classes annotated with CommandInfo.
     */
    public CommandExecutionController(final FileSystemModel fileSystemModel, final String packageToScan) {
        this.commandList = new HashMap<>();
        this.fileSystemModel = fileSystemModel;

        initializeAllCommands(packageToScan);
    }

    private void initializeAllCommands(final String packageToScan) {
        // 1. Scan the package to find all the classes that implement the CommandInterface AND are annotated with CommandInfo.
        final Reflections reflections = new Reflections(packageToScan);
        final Set<Class<?>> annotatedClasses = reflections.getTypesAnnotatedWith(CommandInfo.class);

        for (final Class<?> annotatedClass : annotatedClasses) {
            // 2. Check if the current class implements the CommandInterface.
            if (CommandInterface.class.isAssignableFrom(annotatedClass)) {
                // 3. Check if there is the CommandInfo annotation for the current command class.
                final CommandInfo annotation = annotatedClass.getAnnotation(CommandInfo.class);
                if (annotation != null) {
                    commandList.put(annotation.name(), annotatedClass.asSubclass(CommandInterface.class));
                }
            }
        }
    }

    /**
     * @brief Attempts to retrieve the correct command class to execute and returns it.
     * @param input: user input that represents a given command such as [CommandName (opt: arguments)]
     * @description: This function attempts to parse the given command. It makes a syntax check (command name
     * and number of arguments provided for the given command name) and, if the check succeeds, instantiates
     * the correct command object and returns it for execution.
     * @throws WrongCommandArgumentNumber: if the number of arguments for the given command is incorrect
     * @throws WrongCommandName: if the provided command name does not exist.
     *
     * @implNote: All other exceptions that are thrown signal an implementation error and are thus tested.
     * For example, if a command's constructor contains the wrong parameters, this function will not be able
     * to instantiate the command and therefore it will throw InstantationException.
     */
    public CommandInterface getDispatchedCommand(String input)
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException
    {
        final String[] commandParts = input.split("\\s+");
        final String commandName = commandParts[0];
        if (commandList.containsKey(commandName)) {
            final Class<? extends CommandInterface> genericCommand = commandList.get(commandName);
            final CommandInfo annotation = genericCommand.getAnnotation(CommandInfo.class);
            final String[] arguments = Arrays.copyOfRange(commandParts, 1, commandParts.length);

            if (annotation.totalArguments() != arguments.length) {
                throw new WrongCommandArgumentNumber("Wrong number of arguments for command: " + commandName
                + " [Expected: " + annotation.totalArguments() + ", provided: " + arguments.length + "]");
            }
            return arguments.length > 0
                    ? genericCommand.getConstructor(FileSystemModel.class, String[].class).newInstance(fileSystemModel, arguments)
                    : genericCommand.getConstructor(FileSystemModel.class).newInstance(fileSystemModel);
        }
        throw new WrongCommandName("Command not found: " + commandName);
    }
}
