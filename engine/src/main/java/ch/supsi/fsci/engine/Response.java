package ch.supsi.fsci.engine;

public class Response {
    private final String key;
    private final String[] additionalParameters;

    public Response(final String key, final String... additionalParameters) {
        this.key = key;
        this.additionalParameters = additionalParameters;
    }

    public String localize() {
        final String localizedMessage = Localization.getSingleton().localize(key);

        if (additionalParameters != null && additionalParameters.length > 0) {
            System.out.println(localizedMessage);
            return String.format(localizedMessage, (Object[]) additionalParameters);
        }

        return localizedMessage;
    }

}
