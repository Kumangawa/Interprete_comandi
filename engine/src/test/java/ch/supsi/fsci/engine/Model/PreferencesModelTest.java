package ch.supsi.fsci.engine.Model;

import ch.supsi.fsci.engine.Data.PreferencesData;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PreferencesModelTest {
    private PreferencesModel preferencesModel;
    private static String testFilePath;

    @BeforeEach
    public void setUp() {
        PreferencesData preferencesData = new PreferencesData();
        testFilePath = Paths.get(preferencesData.getPreferenceFilePath()).getParent().toString() + File.separator + "new_preferences.txt";
        preferencesData.setPreferenceFilePath(testFilePath);
        preferencesModel = new PreferencesModel(preferencesData);
        preferencesModel.setPreference("language", "en");
        preferencesModel.setPreference("commandFieldPrefColumnCount", "80");
        preferencesModel.setPreference("prefOutputAreaRowCount", "25");
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
    public void testGetPreference() {
        assertEquals("en", preferencesModel.getPreference("language"));
        assertEquals("80", preferencesModel.getPreference("commandFieldPrefColumnCount"));
        assertEquals("25", preferencesModel.getPreference("prefOutputAreaRowCount"));
    }

    @Test
    public void testSetPreference() {
        preferencesModel.setPreference("language", "it");
        assertEquals("it", preferencesModel.getPreference("language"));
    }

    @Test
    public void testGetKeys() {
        assertTrue(preferencesModel.getKeys().contains("language"));
        assertTrue(preferencesModel.getKeys().contains("commandFieldPrefColumnCount"));
        assertTrue(preferencesModel.getKeys().contains("prefOutputAreaRowCount"));
    }
}
