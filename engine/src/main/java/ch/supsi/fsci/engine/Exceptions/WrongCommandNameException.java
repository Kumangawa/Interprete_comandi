package ch.supsi.fsci.engine.Exceptions;

public class WrongCommandNameException extends ApplicationBaseException {
    public WrongCommandNameException(final String key, final String... additionalParameters) {
        super(key, additionalParameters);
    }
}