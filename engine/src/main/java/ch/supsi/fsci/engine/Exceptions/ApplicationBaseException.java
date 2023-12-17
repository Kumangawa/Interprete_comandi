package ch.supsi.fsci.engine.Exceptions;

public class ApplicationBaseException extends RuntimeException {
    private final String key;
    private final String[] additionalParameters;

    public ApplicationBaseException(final String key, final String... additionalParameters) {
        super(formatMessage(key, additionalParameters));
        this.key = key;
        this.additionalParameters = additionalParameters;
    }

    public String getKey() {
        return key;
    }

    public String[] getAdditionalParameters() {
        return additionalParameters.clone();
    }

    private static String formatMessage(String key, String... additionalParameters) {
        return String.format("%s %s", key, String.join(" ", additionalParameters));
    }
}
