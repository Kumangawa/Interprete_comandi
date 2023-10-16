package ch.supsi.fsci.engine;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Localization {

    private static boolean inizialize = false;
    private static String bundleName;

    private static Locale locale ;

    private static ResourceBundle translations;

    public Localization(){
        if(!inizialize){
            bundleName =  "i18n.translations";
            locale = Locale.forLanguageTag("en");
            translations = ResourceBundle.getBundle(bundleName, locale);
        }
    }

    public static void initialize(String bundleNametmp, Locale localetmp) {
        bundleName = bundleNametmp;
        locale = localetmp;
        translations = ResourceBundle.getBundle(bundleName, locale);
        inizialize = true;
    }

    public static ResourceBundle getResourceBundle() {
        return translations;
    }

    public static boolean isInitialized() {
        return inizialize;
    }

    public static String localize(String key) {
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

