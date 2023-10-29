package ch.supsi.fsci.engine.Controller;

import ch.supsi.fsci.engine.Model.PreferencesModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PreferencesControllerTest {
    PreferencesController preferencesController;

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
    public void testGetPreference(){
        PreferencesModel preferencesModel = preferencesController.loadPreferences();
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
