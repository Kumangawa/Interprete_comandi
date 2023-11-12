package ch.supsi.fsci.client;

import ch.supsi.fsci.engine.Localization;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.Test;
import org.testfx.matcher.control.TextInputControlMatchers;

import static javafx.scene.input.KeyCode.ENTER;
import static org.testfx.api.FxAssert.verifyThat;

public class MainFxWrongCommandNameTest extends AbstractMainGUITest{

    @Test
    public void testWrongCommandName() {
        step("Test wrong command name", () -> {
            interact(() -> {
                TextField commandTextField = lookup("#commandTextField").query();
                commandTextField.setText("abc");
            });
            sleep(SLEEP_INTERVAL);
            interact(() -> type(ENTER));
            verifyThat("#commandTextField", TextInputControlMatchers.hasText(""));
            String a = String.format(Localization.getSingleton().localize("WrongCommand.NameException"), "abc");
            verifyThat("#outputArea", TextInputControlMatchers.hasText(a + "\n"));
        });
    }
}
