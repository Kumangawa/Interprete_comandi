package ch.supsi.fsci.engine.Exceptions;

public class DirectoryNotFound extends ApplicationBaseException {
    public DirectoryNotFound(final String key, final String... additionalParameters) {
        super(key, additionalParameters);
    }
}