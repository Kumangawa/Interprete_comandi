package ch.supsi.fsci.engine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LocalizationTest {

    @BeforeEach
    public void setUp() {
        Locale.setDefault(Locale.forLanguageTag("en"));
    }

    @Test
    public void testInitialize() {
        Localization.getSingleton().initialize("i18n.translations", Locale.forLanguageTag("it"));
        ResourceBundle bundle = Localization.getSingleton().getResourceBundle();
        assertEquals("it", bundle.getLocale().getLanguage());
        assertTrue(Localization.getSingleton().isInitialized());
    }

    @Test
    public void testLocalizeMissingKey() {
        Localization.getSingleton().initialize("i18n.translations", Locale.forLanguageTag("en"));
        String localizedString = Localization.getSingleton().localize("missing.key");
        assertEquals("missing.key", localizedString);
    }
}