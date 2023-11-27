package ch.supsi.fsci.engine.Data;

import ch.supsi.fsci.engine.Model.PreferencesModel;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Set;

public class PreferencesData {
    private static String PREFERENCE_FILE_PATH = System.getProperty("user.home") + File.separator + "GUI" + File.separator + "preferences.txt";

    private void createFolderGUI() {
        try {
            Path defaultPath = Paths.get(PREFERENCE_FILE_PATH).getParent();
            Files.createDirectories(defaultPath);
            Files.setAttribute(defaultPath, "dos:hidden", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Properties loadProperties() {
        Properties defaultProps = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("preferences.properties")) {
            if (input == null) {
                return defaultProps;
            }
            defaultProps.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return defaultProps;
    }

    public void savePreferences(PreferencesModel preferences) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PREFERENCE_FILE_PATH))) {
            Set<String> keys = preferences.getKeys();
            for (String key : keys) {
                String value = preferences.getPreference(key);
                writer.write(key + ": " + value);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setPreferenceFilePath(String path) {
        PREFERENCE_FILE_PATH = path;
    }

    public String getPreferenceFilePath() {
        return PREFERENCE_FILE_PATH;
    }

    public PreferencesModel loadPreferences() {
        PreferencesModel preferences = new PreferencesModel(loadProperties());
        File preferenceFile = new File(PREFERENCE_FILE_PATH);
        if (preferenceFile.exists()) {

            try (BufferedReader reader = new BufferedReader(new FileReader(PREFERENCE_FILE_PATH))) {
                Properties properties = new Properties();
                properties.load(reader);

                for (String key : properties.stringPropertyNames()) {
                    preferences.setPreference(key, properties.getProperty(key));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            createFolderGUI();
        }
        savePreferences(preferences);
        return preferences;
    }
}
