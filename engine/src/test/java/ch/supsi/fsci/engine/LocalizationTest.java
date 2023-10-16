package ch.supsi.fsci.engine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Locale;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LocalizationTest {

    @BeforeEach
    public void setUp() {
        // Imposta la lingua predefinita per i test
        Locale.setDefault(Locale.forLanguageTag("en"));
    }

    @Test
    public void testInitialize() {
        Localization.initialize("i18n.translations", Locale.forLanguageTag("it"));
        ResourceBundle bundle = Localization.getResourceBundle();
        assertEquals("it", bundle.getLocale().getLanguage());
        assertTrue(Localization.isInitialized());
    }

    @Test
    public void testLocalizeExistingKey() {
        Localization.initialize("i18n.translations", Locale.forLanguageTag("en"));
        String localizedString = Localization.localize("test.key");
        assertEquals("Existing key value", localizedString);
    }

    @Test
    public void testLocalizeMissingKey() {
        Localization.initialize("i18n.translations", Locale.forLanguageTag("en"));
        String localizedString = Localization.localize("missing.key");
        assertEquals("missing.key", localizedString);
    }
}

