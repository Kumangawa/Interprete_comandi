package ch.supsi.fsci.client;

import ch.supsi.fsci.engine.Localization;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.Test;
import org.testfx.matcher.control.TextInputControlMatchers;

import static javafx.scene.input.KeyCode.ENTER;
import static org.testfx.api.FxAssert.verifyThat;

public class MainFxMkdirCommandTest  extends AbstractMainGUITest {

    @Test
    public void testMkdirCommand() {
        String lettera = "A";
        String a = String.format(Localization.getSingleton().localize("command.mkdir"),lettera) +"\n";
        String path = "/A/B";
        String secondLetter = "B";
        String b = String.format(Localization.getSingleton().localize("command.mkdir"),secondLetter) +"\n";

        step("Test command mkdir, relative path", () -> {
            interact(() -> {
                TextField commandTextField = lookup("#commandTextField").query();
                commandTextField.setText("mkdir "+lettera);
            });
            sleep(SLEEP_INTERVAL);
            interact(() -> type(ENTER));
            verifyThat("#commandTextField", TextInputControlMatchers.hasText(""));
            verifyThat("#outputArea", TextInputControlMatchers.hasText(a));
        });

        step("Test command mkdir, absolute path", () -> {
            interact(() -> {
                TextField commandTextField = lookup("#commandTextField").query();
                commandTextField.setText("mkdir "+path);
            });
            sleep(SLEEP_INTERVAL);
            interact(() -> type(ENTER));
            verifyThat("#commandTextField", TextInputControlMatchers.hasText(""));
            verifyThat("#outputArea", TextInputControlMatchers.hasText(a+b));});
        }
    }

