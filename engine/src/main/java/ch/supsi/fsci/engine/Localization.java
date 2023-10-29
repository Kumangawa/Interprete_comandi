package ch.supsi.fsci.engine;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Localization {
    private boolean inizialize = false;
    private String bundleName;

    private Locale locale ;

    private ResourceBundle translations;

    private static final Localization singleton = new Localization();

    private Localization(){
        bundleName =  "i18n.translations";
        locale = Locale.forLanguageTag("en");
        translations = ResourceBundle.getBundle(bundleName, locale);
    }

    public static Localization getSingleton() {
        return singleton;
    }

    public void initialize(String bundleNametmp, Locale localetmp) {
        bundleName = bundleNametmp;
        locale = localetmp;
        translations = ResourceBundle.getBundle(bundleName, locale);
        inizialize = true;
    }

    public ResourceBundle getResourceBundle() {
        return translations;
    }

    public boolean isInitialized() {
        return inizialize;
    }

    public String localize(String key) {
        if (key == null || key.isEmpty()) {
            return "";
        }

        String translation;

        try {
            translation = translations.getString(key);

        } catch (MissingResourceException e) {
            translation = key;
        }

        return translation;
    }

}

