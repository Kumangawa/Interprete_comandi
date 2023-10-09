package ch.supsi.fsci.engine.Exceptions;

public class WrongCommandNameException extends RuntimeException {
    public WrongCommandNameException(final String commandName) {
        super("Command not found: " + commandName);
    }
}
