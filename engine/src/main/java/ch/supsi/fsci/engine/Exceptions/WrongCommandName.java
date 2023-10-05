package ch.supsi.fsci.engine.Exceptions;

public class WrongCommandName extends RuntimeException {
    public WrongCommandName(final String message) {
        super(message);
    }
}
