package ch.supsi.fsci.client;
import ch.supsi.fsci.engine.Localization;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.Test;
import org.testfx.matcher.control.TextInputControlMatchers;

import static javafx.scene.input.KeyCode.ENTER;
import static org.testfx.api.FxAssert.verifyThat;

public class MainFxCdCommandTest  extends AbstractMainGUITest {
    @Test
    public void testCdCommand() {
        step("Test command cd", () -> {
            interact(() -> {
                TextField commandTextField = lookup("#commandTextField").query();
                commandTextField.setText("mkdir A");
            });

            sleep(SLEEP_INTERVAL);

            interact(() -> type(ENTER));

            verifyThat("#commandTextField", TextInputControlMatchers.hasText(""));

            verifyThat("#outputArea", TextInputControlMatchers.hasText(String.format(Localization.getSingleton().localize("command.mkdir")) +"A\n"));



            interact(() -> {
                TextField commandTextField = lookup("#commandTextField").query();
                commandTextField.setText("cd A");
            });

            sleep(SLEEP_INTERVAL);

            interact(() -> type(ENTER));

            verifyThat("#commandTextField", TextInputControlMatchers.hasText(""));

            verifyThat("#outputArea", TextInputControlMatchers.hasText(String.format(Localization.getSingleton().localize("command.mkdir")) + "A" + "\n" + "A" + "\n"));
        });
    }
}
