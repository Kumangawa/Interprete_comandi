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
        String command = "abc";
        String a = String.format(Localization.getSingleton().localize("WrongCommand.NameException"), command) + "\n";
        step("Test wrong command name", () -> {
            interact(() -> {
                TextField commandTextField = lookup("#commandTextField").query();
                commandTextField.setText(command);
            });
            sleep(SLEEP_INTERVAL);
            interact(() -> type(ENTER));
            verifyThat("#commandTextField", TextInputControlMatchers.hasText(""));
            verifyThat("#outputArea", TextInputControlMatchers.hasText(a));
        });
    }
}
