package ch.supsi.fsci.client;

import ch.supsi.fsci.engine.Localization;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.Test;
import org.testfx.matcher.control.TextInputControlMatchers;

import static javafx.scene.input.KeyCode.ENTER;
import static org.testfx.api.FxAssert.verifyThat;

public class MainFxPwdCommandTest  extends AbstractMainGUITest {

    @Test
    public void testPwdCommand() {
        step("Test command pwd", () -> {
            interact(() -> {
                TextField commandTextField = lookup("#commandTextField").query();
                commandTextField.setText("pwd");
            });

            sleep(SLEEP_INTERVAL);
            interact(() -> type(ENTER));
            verifyThat("#commandTextField", TextInputControlMatchers.hasText(""));
            String a = String.format(Localization.getSingleton().localize("command.pwd")) +"/\n";
            verifyThat("#outputArea", TextInputControlMatchers.hasText(a));


            interact(() -> {
                TextField commandTextField = lookup("#commandTextField").query();
                commandTextField.setText("mkdir A");
            });
            sleep(SLEEP_INTERVAL);
            interact(() -> type(ENTER));
            verifyThat("#commandTextField", TextInputControlMatchers.hasText(""));
            String b = String.format(Localization.getSingleton().localize("command.mkdir")) +"A\n";
            verifyThat("#outputArea", TextInputControlMatchers.hasText(a + b));


            interact(() -> {
                TextField commandTextField = lookup("#commandTextField").query();
                commandTextField.setText("cd A");
            });
            sleep(SLEEP_INTERVAL);
            interact(() -> type(ENTER));
            verifyThat("#commandTextField", TextInputControlMatchers.hasText(""));
            String c = "A\n";
            verifyThat("#outputArea", TextInputControlMatchers.hasText(a+b+c));


            interact(() -> {
                TextField commandTextField = lookup("#commandTextField").query();
                commandTextField.setText("pwd");
            });

            sleep(SLEEP_INTERVAL);
            interact(() -> type(ENTER));
            verifyThat("#commandTextField", TextInputControlMatchers.hasText(""));
            String d = String.format(Localization.getSingleton().localize("command.pwd")) +"/A\n";
            verifyThat("#outputArea", TextInputControlMatchers.hasText(a+b+c+d));
        });
    }
}
