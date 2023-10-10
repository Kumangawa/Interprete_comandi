package ch.supsi.fsci.engine.Exceptions;

public class WrongCommandArgumentNumberException extends RuntimeException {
    public WrongCommandArgumentNumberException(final String commandName, final String commandSyntax, int expected, int provided) {
        super("Wrong number of arguments for command: " + commandName
                + " [Expected: " + expected + ", provided: " + provided + "]! Correct usage: " +
                commandSyntax);
    }
}
