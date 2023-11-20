package ch.supsi.fsci.client;

import ch.supsi.fsci.engine.Localization;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.Test;
import org.testfx.matcher.control.TextInputControlMatchers;

import static javafx.scene.input.KeyCode.ENTER;
import static org.testfx.api.FxAssert.verifyThat;

public class MainFxDirectoryNotFoundTest extends AbstractMainGUITest{

    @Test
    public void testDirectoryNotFound() {
        String lettera = "A";
        String a = String.format(Localization.getSingleton().localize("DirectoryNotFound"), lettera, "["+ lettera +"]") + "\n";
        step("Test directory not found", () -> {
            interact(() -> {
                TextField commandTextField = lookup("#commandTextField").query();
                commandTextField.setText("cd /"+lettera);
            });
            sleep(SLEEP_INTERVAL);
            interact(() -> type(ENTER));
            verifyThat("#commandTextField", TextInputControlMatchers.hasText(""));
            verifyThat("#outputArea", TextInputControlMatchers.hasText(a));
        });
    }
}
