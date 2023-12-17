package ch.supsi.fsci.engine.Data;

import java.io.*;
import java.nio.file.*;

import java.util.Arrays;
import java.util.Properties;

public class PreferencesData {
    private static String PREFERENCE_FILE_PATH = System.getProperty("user.home") + File.separator + "GUI" + File.separator + "preferences.txt";
    private static final String[] REQUIRED_KEYS = {
            "commandFieldPrefColumnCount",
            "language",
            "prefOutputAreaRowCount"
    };

    public void savePreferences(Properties properties) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(PREFERENCE_FILE_PATH))) {
            properties.store(writer, null);
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

    public Properties loadPreferences() {
        File preferenceFile = new File(PREFERENCE_FILE_PATH);
        Properties preferences = new Properties();

        if (preferenceFile.exists()) {
            try (BufferedReader reader = Files.newBufferedReader(Paths.get(PREFERENCE_FILE_PATH))) {
                preferences.load(reader);
                if (!hasAllRequiredKeys(preferences)) {
                    clearFile();
                    createDefaultPreferences(preferences);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            createFolderGUI();
            createDefaultPreferences(preferences);
        }
        return preferences;
    }

    private boolean hasAllRequiredKeys(Properties preferences) {
        return Arrays.stream(REQUIRED_KEYS).allMatch(preferences::containsKey);
    }


    private void createDefaultPreferences(Properties preferences) {
        preferences.clear();
        preferences.setProperty("commandFieldPrefColumnCount", "80");
        preferences.setProperty("language", "en");
        preferences.setProperty("prefOutputAreaRowCount", "25");
        savePreferences(preferences);
    }

    private void clearFile() {
        try (BufferedWriter ignored = Files.newBufferedWriter(Paths.get(PREFERENCE_FILE_PATH), StandardOpenOption.TRUNCATE_EXISTING)) {
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createFolderGUI() {
        try {
            Path defaultPath = Paths.get(PREFERENCE_FILE_PATH).getParent();
            Files.createDirectories(defaultPath);
            Files.setAttribute(defaultPath, "dos:hidden", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
