package ch.supsi.fsci.client;

import ch.supsi.fsci.engine.Localization;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.Test;
import org.testfx.matcher.control.TextInputControlMatchers;

import static javafx.scene.input.KeyCode.ENTER;
import static org.testfx.api.FxAssert.verifyThat;

public class MainFxWrongCommandNumberTest extends AbstractMainGUITest{

    @Test
    public void testWrongCommandNumber() {
        step("Test wrong command number", () -> {
            interact(() -> {
                TextField commandTextField = lookup("#commandTextField").query();
                commandTextField.setText("pwd due");
            });
            sleep(SLEEP_INTERVAL);
            interact(() -> type(ENTER));
            verifyThat("#commandTextField", TextInputControlMatchers.hasText(""));
            String a = String.format(Localization.getSingleton().localize("WrongCommand.ArgumentNumberException"), "pwd",0,1,"pwd");
            verifyThat("#outputArea", TextInputControlMatchers.hasText(a + "\n"));
        });
    }
}
