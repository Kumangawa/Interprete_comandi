package ch.supsi.fsci.engine.Data;

import ch.supsi.fsci.engine.Model.PreferencesModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PreferencesDataTest {
    private PreferencesData preferencesData;
    private String testFilePath;

    @BeforeEach
    public void setUp() {
        preferencesData = new PreferencesData();
        testFilePath = Paths.get(preferencesData.getPreferenceFilePath()).getParent().toString() + File.separator + "new_preferences.txt";
        preferencesData.setPreferenceFilePath(testFilePath);
    }

    @Test
    public void testSaveAndLoadPreferences() {
        PreferencesModel originalPreferences = preferencesData.loadPreferences();
        originalPreferences.setPreference("key1", "value1");
        originalPreferences.setPreference("key2", "value2");
        preferencesData.savePreferences(originalPreferences);
        PreferencesModel loadedPreferences = preferencesData.loadPreferences();
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
