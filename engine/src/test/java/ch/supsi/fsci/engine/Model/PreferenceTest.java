package ch.supsi.fsci.engine.Model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PreferenceTest {
    private Preference preference;

    @BeforeEach
    public void setUp() {
        preference = new Preference();
    }

    @Test
    public void testDefaultConstructor() {
        assertEquals("en", preference.getLanguage());
        assertEquals(11, preference.getPrefCommandSpacerWidth());
        assertEquals(80, preference.getCommandFieldPrefColumnCount());
        assertEquals(25, preference.getPrefOutputAreaRowCount());
        assertEquals(7, preference.getPrefInsetsSize());
    }


    @Test
    public void testSetLanguage() {
        preference.setPreference("language", "it");
        assertEquals("it", preference.getLanguage());
    }

    @Test
    public void testSetPrefCommandSpacerWidth() {
        preference.setPreference("prefCommandSpacerWidth", "15");
        assertEquals(15, preference.getPrefCommandSpacerWidth());
    }

    @Test
    public void testSetCommandFieldPrefColumnCount() {
        preference.setPreference("commandFieldPrefColumnCount", "100");
        assertEquals(100, preference.getCommandFieldPrefColumnCount());
    }

    @Test
    public void testSetPrefOutputAreaRowCount() {
        preference.setPreference("prefOutputAreaRowCount", "30");
        assertEquals(30, preference.getPrefOutputAreaRowCount());
    }

    @Test
    public void testSetPrefInsetsSize() {
        preference.setPreference("prefInsetsSize", "5");
        assertEquals(5, preference.getPrefInsetsSize());
    }

    @Test
    public void testSetInvalidPreferenceKey() {
        preference.setPreference("invalidKey", "value");
        assertEquals("en", preference.getLanguage());
        assertEquals(11, preference.getPrefCommandSpacerWidth());
        assertEquals(80, preference.getCommandFieldPrefColumnCount());
        assertEquals(25, preference.getPrefOutputAreaRowCount());
        assertEquals(7, preference.getPrefInsetsSize());
    }
}
