package ch.supsi.engine.Model.CommandPattern.Commands;

import ch.supsi.fsci.engine.CommandPattern.CommandInfo;
import ch.supsi.fsci.engine.CommandPattern.CommandInterface;
import ch.supsi.fsci.engine.CommandPattern.Commands.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;

import java.util.Set;

public class CommandsTest {
    @Test
    public void testCommandsAnnotations() {
        // All commands must be annotated with the CommandInfo, describing command name and command number of arguments.
        final Reflections reflections = new Reflections("ch.supsi.fsci.engine.CommandPattern.Commands");
        final Set<Class<?>> annotatedClasses = reflections.getTypesAnnotatedWith(CommandInfo.class);

        for (final Class<?> annotatedClass : annotatedClasses) {
            final CommandInfo annotation = annotatedClass.getAnnotation(CommandInfo.class);
            Assertions.assertNotNull(annotation);
        }
    }

    @Test
    public void testCommandsImplementInterface() {
        // All commands must implement the CommandInterface
        final Reflections reflections = new Reflections("ch.supsi.fsci.engine.CommandPattern.Commands");
        final Set<Class<?>> annotatedClasses = reflections.getTypesAnnotatedWith(CommandInfo.class);

        for (final Class<?> annotatedClass : annotatedClasses) {
            Assertions.assertTrue(CommandInterface.class.isAssignableFrom(annotatedClass));
        }
    }

    private void testCommandAnnotationNameInternal(Class<?> commandClass, String expectedName) {
        Assertions.assertTrue(commandClass.isAnnotationPresent(CommandInfo.class));
        CommandInfo annotation = commandClass.getAnnotation(CommandInfo.class);
        Assertions.assertNotNull(annotation);
        Assertions.assertEquals(expectedName, annotation.name());
    }

    private void testCommandAnnotationArgumentsNumberInternal(Class<?> commandClass, int expectedTotalArguments) {
        Assertions.assertTrue(commandClass.isAnnotationPresent(CommandInfo.class));
        CommandInfo annotation = commandClass.getAnnotation(CommandInfo.class);
        Assertions.assertNotNull(annotation);
        Assertions.assertEquals(expectedTotalArguments, annotation.totalArguments());
    }

    @Test
    public void testCommandsAnnotationsNames() {
        testCommandAnnotationNameInternal(CdCommand.class, "cd");
        testCommandAnnotationNameInternal(HelpCommand.class, "help");
        testCommandAnnotationNameInternal(LsCommand.class, "ls");
        testCommandAnnotationNameInternal(MkdirCommand.class, "mkdir");
        testCommandAnnotationNameInternal(MvCommand.class, "mv");
        testCommandAnnotationNameInternal(PwdCommand.class, "pwd");
        testCommandAnnotationNameInternal(RmCommand.class, "rm");
    }

    @Test
    public void testCommandsAnnotationsArgumentsNumber() {
        testCommandAnnotationArgumentsNumberInternal(CdCommand.class, 1);
        testCommandAnnotationArgumentsNumberInternal(HelpCommand.class, 0);
        testCommandAnnotationArgumentsNumberInternal(LsCommand.class, 0);
        testCommandAnnotationArgumentsNumberInternal(MkdirCommand.class, 1);
        testCommandAnnotationArgumentsNumberInternal(MvCommand.class, 2);
        testCommandAnnotationArgumentsNumberInternal(PwdCommand.class, 0);
        testCommandAnnotationArgumentsNumberInternal(RmCommand.class, 1);
    }
}
