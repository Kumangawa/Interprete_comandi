package ch.supsi.fsci.engine.CommandDispatcher;

import ch.supsi.fsci.engine.Model.FileSystemModel;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Set;

import org.reflections.Reflections;


public class CommandExecutionController {
    private final HashMap<String, Class<? extends CommandInterface>> commandList;
    private final FileSystemModel fileSystemModel;

    public CommandExecutionController(final FileSystemModel fileSystemModel) {
        this.commandList = new HashMap<>();
        this.fileSystemModel = fileSystemModel;

        initializeAllCommands();
    }

    private void initializeAllCommands() {
        // Self notes
        // 1. Scan the current package to find all the classes that implement the CommandInterface AND are annotated with CommandInfo.
        final Package currentPackage = CommandExecutionController.class.getPackage();
        final Reflections reflections = new Reflections(currentPackage.getName());
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

    public void register(final Class<? extends CommandInterface> commandClass) {
        final CommandInfo annotation = commandClass.getAnnotation(CommandInfo.class);
        if (annotation != null) {
            commandList.put(annotation.name(), commandClass);
        } else {
            throw new IllegalArgumentException("No CommandInfo annotation found: " + commandClass.getName());
        }
    }

    public CommandInterface getDispatchedCommand(final String commandName, final String... arguments)
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException
    {
        if (commandList.containsKey(commandName)) {
            final Class<? extends CommandInterface> genericCommand = commandList.get(commandName);
            final CommandInfo annotation = genericCommand.getAnnotation(CommandInfo.class);

            if (annotation.totalArguments() != arguments.length) {
                throw new IllegalArgumentException("Wrong number of arguments for command: " + commandName
                + " [Expected: " + annotation.totalArguments() + ", provided: " + arguments.length + "]");
            }

            return arguments.length > 0
                    ? genericCommand.getConstructor(FileSystemModel.class, String[].class).newInstance(fileSystemModel, arguments)
                    : genericCommand.getConstructor(FileSystemModel.class).newInstance(fileSystemModel);
        }
        throw new IllegalArgumentException("Command not found: " + commandName);
    }
}
