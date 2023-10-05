package ch.supsi.fsci.engine.Exceptions;

public class WrongCommandArgumentNumber extends RuntimeException {
    public WrongCommandArgumentNumber(final String message) {
        super(message);
    }
}
