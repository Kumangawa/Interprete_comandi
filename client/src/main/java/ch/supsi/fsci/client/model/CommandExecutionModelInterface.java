package ch.supsi.fsci.client.model;

import ch.supsi.fsci.engine.CommandPattern.CommandInterface;

import java.lang.reflect.InvocationTargetException;

public interface CommandExecutionModelInterface {
    void initializeAllCommands(final String packageToScan);
    CommandInterface getDispatchedCommand(final String input) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;
}
