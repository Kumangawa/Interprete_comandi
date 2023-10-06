package ch.supsi.fsci.engine.Exceptions;

public class WrongCommandArgumentNumber extends RuntimeException {
    public WrongCommandArgumentNumber(final String commandName, int expected, int provided) {
        super("Wrong number of arguments for command: " + commandName
                + " [Expected: " + expected + ", provided: " + provided + "]");
    }
}
