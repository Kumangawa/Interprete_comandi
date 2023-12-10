
package ch.supsi.fsci.engine.Exceptions;

public class WrongCommandArgumentNumberException extends ApplicationBaseException {
    public WrongCommandArgumentNumberException(final String key, final String... additionalParameters) {
        super(key, additionalParameters);
    }
}