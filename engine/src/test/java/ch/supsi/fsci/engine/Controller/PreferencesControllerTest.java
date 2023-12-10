package ch.supsi.fsci.engine.Controller;import ch.supsi.fsci.engine.Data.PreferencesData;
import ch.supsi.fsci.engine.Interface.PreferencesInterface;
import ch.supsi.fsci.engine.Model.PreferencesModel;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PreferencesControllerTest {

    private PreferencesModel preferencesModel;
    private PreferencesData preferencesData;
    private PreferencesController preferencesController;
    private static String testFilePath;


    @BeforeEach
    public void setUp() {
        preferencesData = new PreferencesData();
        preferencesModel = new PreferencesModel(preferencesData);
        preferencesController = new PreferencesController(preferencesModel);
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
    public void testGetPreference() {
        String key = "testKey";
        String value = "testValue";
        preferencesModel.setPreference(key, value);
        assertEquals(value, preferencesController.getPreference(key));
    }

    @Test
    public void testSetAndGetPreferenceFilePath() {
        String path = "/test/path";
        preferencesController.setPreferenceFilePath(path);
        assertEquals(path, preferencesController.getPreferenceFilePath());
    }

    @Test
    public void testSetPreference() {
        String key = "testKey";
        String value = "testValue";
        preferencesController.setPreference(key, value);
        assertEquals(value, preferencesModel.getPreference(key));
    }

    @Test
    public void testSavePreferences() {
        String key = "testKey";
        String value = "testValue";
        preferencesModel.setPreference(key, value);
        Properties properties = preferencesData.loadPreferences();
        assertEquals(value, properties.get(key));
    }

    @Test
    public void testGetPreferencesModel() {
        PreferencesInterface result = preferencesController.getPreferencesModel();
        assertEquals(preferencesModel, result);
    }
}
