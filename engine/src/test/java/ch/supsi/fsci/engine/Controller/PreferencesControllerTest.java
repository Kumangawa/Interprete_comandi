package ch.supsi.fsci.engine.Controller;import ch.supsi.fsci.engine.Data.PreferencesData;
import ch.supsi.fsci.engine.Model.PreferencesModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PreferencesControllerTest {

    private PreferencesModel preferencesModel;
    private PreferencesData preferencesData;
    private PreferencesController preferencesController;

    @BeforeEach
    public void setUp() {
        preferencesData = new PreferencesData();
        preferencesModel = preferencesData.loadPreferences();
        preferencesController = new PreferencesController(preferencesModel, preferencesData);
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
        preferencesController.savePreferences();
        PreferencesModel loadedPreferences = preferencesData.loadPreferences();
        assertEquals(value, loadedPreferences.getPreference(key));
    }

    @Test
    public void testGetPreferencesModel() {
        PreferencesModel result = preferencesController.getPreferencesModel();
        assertEquals(preferencesModel, result);
    }
}
