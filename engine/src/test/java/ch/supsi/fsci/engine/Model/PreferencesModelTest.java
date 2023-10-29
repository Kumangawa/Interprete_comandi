package ch.supsi.fsci.engine.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PreferencesModelTest {
    private PreferencesModel preferencesModel;

    @BeforeEach
    public void setUp() {
        Properties defaultProperties = new Properties();
        defaultProperties.setProperty("language", "en");
        defaultProperties.setProperty("commandFieldPrefColumnCount", "80");
        defaultProperties.setProperty("prefOutputAreaRowCount", "25");

        preferencesModel = new PreferencesModel(defaultProperties);
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
