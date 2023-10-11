package ch.supsi.fsci.engine;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class PersistenceTest {
    Persistence persistence;

    @BeforeEach
    public void setUp() {
        persistence = new Persistence();
        String path = Paths.get(persistence.getPreferenceFilePath()).getParent().toString();
        path += File.separator + "test_preferences.txt";
        persistence.setPreferenceFilePath(path);

    }

    @Test
    public void testInitializeExplicit(){
        persistence.initializeExplicit();
        assertTrue(Files.exists(Paths.get(persistence.getPreferenceFilePath()).getParent()));
        assertTrue(new File(persistence.getPreferenceFilePath()).exists());
    }


    @Test
    public void testGetPreference(){
        persistence.initializeExplicit();
        HashMap<String, String> preferencesData = persistence.getPreference();
        assertEquals(5, preferencesData.size());
        assertEquals("en", preferencesData.get("language"));
        assertEquals("11", preferencesData.get("prefCommandSpacerWidth"));
        assertEquals("80", preferencesData.get("commandFieldPrefColumnCount"));
        assertEquals("25", preferencesData.get("prefOutputAreaRowCount"));
        assertEquals("7", preferencesData.get("prefInsetsSize"));
    }

    @AfterEach
    public void cleanupPreferenceFile() {
        File preferenceFile = new File(persistence.getPreferenceFilePath());
        if (preferenceFile.exists()) {
            preferenceFile.delete();
        }
    }
}

