package ch.supsi.fsci.engine.Exceptions;

public class WrongCommandName extends RuntimeException {
    public WrongCommandName(final String commandName) {
        super("Command not found: " + commandName);
    }
}
