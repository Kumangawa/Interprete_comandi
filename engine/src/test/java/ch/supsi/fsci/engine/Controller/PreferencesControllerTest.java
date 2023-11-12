package ch.supsi.fsci.engine.Controller;

import ch.supsi.fsci.engine.Model.PreferencesModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class PreferencesControllerTest {
    private PreferencesController preferencesController;

    @BeforeEach
    public void setUp() {
        preferencesController = new PreferencesController();
        String path = Paths.get(preferencesController.getPreferenceFilePath()).getParent().toString();
        path += File.separator + "test_preferences.txt";
        preferencesController.setPreferenceFilePath(path);
    }



    @Test
    public void testInitializeExplicit(){
        preferencesController.loadPreferences();
        assertTrue(Files.exists(Paths.get(preferencesController.getPreferenceFilePath()).getParent()));
        assertTrue(new File(preferencesController.getPreferenceFilePath()).exists());
    }

    @Test
    public void saveAndLoadPreferencesTest() {
        final PreferencesModel preferences = preferencesController.loadPreferences();
        preferences.setPreference("language", "it");
        preferences.setPreference("prefOutputAreaRowCount", "30");
        preferencesController.savePreferences(preferences);
        final PreferencesModel loadedPreferences = preferencesController.loadPreferences();
        assertNotNull(loadedPreferences);
        assertEquals("it", loadedPreferences.getPreference("language"));
        assertEquals("30", loadedPreferences.getPreference("prefOutputAreaRowCount"));
    }

    @Test
    public void testGetPreference(){
        PreferencesModel preferencesModel = preferencesController.loadPreferences();
        assertNotNull(preferencesModel);
        assertEquals(3, preferencesModel.getKeys().size());
        assertEquals("en", preferencesModel.getPreference("language"));
        assertEquals("80", preferencesModel.getPreference("commandFieldPrefColumnCount"));
        assertEquals("25", preferencesModel.getPreference("prefOutputAreaRowCount"));
    }

    @AfterEach
    public void cleanupPreferenceFile() throws IOException {
        Files.deleteIfExists(Paths.get(preferencesController.getPreferenceFilePath()));
    }
}
