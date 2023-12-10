package ch.supsi.fsci.engine.Model;

import ch.supsi.fsci.engine.Data.PreferencesData;
import ch.supsi.fsci.engine.Interface.PreferencesInterface;

import java.util.Properties;
import java.util.Set;

public class PreferencesModel implements PreferencesInterface {
    private final PreferencesData preferencesData;
    private final Properties properties;


    public PreferencesModel(final PreferencesData preferencesData) {
        this.preferencesData = preferencesData;
        this.properties = preferencesData.loadPreferences();
    }

    public String getPreferenceFilePath() { return preferencesData.getPreferenceFilePath(); }
    public void setPreferenceFilePath(final String path) { preferencesData.setPreferenceFilePath(path); }
    public String getPreference(String key) { return properties.getProperty(key); }

    public void setPreference(String key, String value) {
        properties.setProperty(key, value);
        preferencesData.savePreferences(properties);
    }

    public Set<String> getKeys() {
        return properties.stringPropertyNames();
    }

}
