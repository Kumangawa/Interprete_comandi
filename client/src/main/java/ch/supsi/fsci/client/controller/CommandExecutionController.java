package ch.supsi.fsci.client.controller;

import ch.supsi.fsci.engine.CommandPattern.CommandInfo;
import ch.supsi.fsci.engine.CommandPattern.CommandInterface;
import ch.supsi.fsci.engine.Exceptions.WrongCommandArgumentNumberException;
import ch.supsi.fsci.engine.Exceptions.WrongCommandNameException;
import ch.supsi.fsci.engine.FileSystemModel;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Set;
import java.util.StringTokenizer;

import ch.supsi.fsci.engine.Localization;
import org.reflections.Reflections;

public class CommandExecutionController {
    // package-protected commandList on purpose for facilitating tests.
    final HashMap<String, Class<? extends CommandInterface>> commandList;
    private final FileSystemModel fileSystemModel;

    /**
     * @brief Initializes the command executor, which takes care of executing the correct commands automatically.
     * @param fileSystemModel: the file system which will be used to execute the command methods.
     */
    public CommandExecutionController(final FileSystemModel fileSystemModel) {
        this.commandList = new HashMap<>();
        this.fileSystemModel = fileSystemModel;
    }

    /**
     * @brief Given a packageToScan, finds all the classes annotated with CommandInfo and completes the dispatcher's initialization
     * @param packageToScan: the package name that contains command classes annotated with CommandInfo.
     */
    public void initializeAllCommands(final String packageToScan) {
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
     * @throws WrongCommandArgumentNumberException : if the number of arguments for the given command is incorrect
     * @throws WrongCommandNameException : if the provided command name does not exist.
     *
     * @implNote: All other exceptions that are thrown signal an implementation error.
     * For example, if a command's constructor contains the wrong parameters, this function will not be able
     * to instantiate the command and therefore it will throw InstantationException.
     */
    public CommandInterface getDispatchedCommand(final String input)
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        final StringTokenizer tokenizer = new StringTokenizer(input);
        if (tokenizer.hasMoreTokens()) {
            final String commandName = tokenizer.nextToken();
            if (commandList.containsKey(commandName)) {
                final Class<? extends CommandInterface> genericCommand = commandList.get(commandName);
                final CommandInfo annotation = genericCommand.getAnnotation(CommandInfo.class);

                if (annotation.totalArguments() != tokenizer.countTokens()) {
                    throw new WrongCommandArgumentNumberException(String.format(Localization.localize("WrongCommand.ArgumentNumberException"),commandName,annotation.totalArguments(),tokenizer.countTokens(),annotation.commandSyntax()));
                }
                return tokenizer.countTokens() > 0
                        ? genericCommand.getConstructor(FileSystemModel.class, StringTokenizer.class).newInstance(fileSystemModel, tokenizer)
                        : genericCommand.getConstructor(FileSystemModel.class).newInstance(fileSystemModel);
            }
            throw new WrongCommandNameException(String.format(Localization.getSingleton().localize("WrongCommand.NameException"), commandName));
        }
        throw new IllegalArgumentException("Input is empty.");
    }
}
