package ch.supsi.fsci.engine.Model;

import java.util.Properties;
import java.util.Set;

public class PreferencesModel {
    private Properties defaultPreferences;

    public PreferencesModel(Properties defaultProperties) {
        defaultPreferences = defaultProperties;
    }

    public String getPreference(String key) {
        return defaultPreferences.getProperty(key);
    }

    public void setPreference(String key, String value) {
        defaultPreferences.setProperty(key, value);
    }

    public Set<String> getKeys() {
        return defaultPreferences.stringPropertyNames();
    }
}
