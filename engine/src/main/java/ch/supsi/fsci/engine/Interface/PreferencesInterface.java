package ch.supsi.fsci.engine.Interface;

import java.util.Set;

public interface PreferencesInterface {
    String getPreference(String key);
    void setPreference(String key, String value);
    Set<String> getKeys();
    String getPreferenceFilePath();
    void setPreferenceFilePath(final String path);

}
