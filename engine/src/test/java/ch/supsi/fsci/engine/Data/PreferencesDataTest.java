package ch.supsi.fsci.engine.Data;

import ch.supsi.fsci.engine.Model.PreferencesModel;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PreferencesDataTest {
    private PreferencesData preferencesData;
    private static String testFilePath;

    @BeforeEach
    public void setUp() {
        preferencesData = new PreferencesData();
        testFilePath = Paths.get(preferencesData.getPreferenceFilePath()).getParent().toString() + File.separator + "new_preferences.txt";
        preferencesData.setPreferenceFilePath(testFilePath);
    }

    @AfterAll
    public static void cleanUp() {
        try {
            Files.deleteIfExists(Paths.get(testFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSaveAndLoadPreferences() {
        PreferencesModel originalPreferences = new PreferencesModel(preferencesData);
        originalPreferences.setPreference("key1", "value1");
        originalPreferences.setPreference("key2", "value2");
        PreferencesModel loadedPreferences = new PreferencesModel(preferencesData);
        assertEquals(originalPreferences.getPreference("key1"), loadedPreferences.getPreference("key1"));
        assertEquals(originalPreferences.getPreference("key2"), loadedPreferences.getPreference("key2"));
    }

    @Test
    public void testGetPreferenceFilePath() {
        assertEquals(testFilePath, preferencesData.getPreferenceFilePath());
    }

    @Test
    public void testCreateFolderGUI() {
        File guiFolder = new File(System.getProperty("user.home") + File.separator + "GUI");
        assertTrue(guiFolder.exists());
        assertTrue(guiFolder.isDirectory());
    }
}
