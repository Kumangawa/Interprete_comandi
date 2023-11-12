package ch.supsi.fsci.client;

import ch.supsi.fsci.engine.Localization;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.Test;
import org.testfx.matcher.control.TextInputControlMatchers;

import static javafx.scene.input.KeyCode.ENTER;
import static org.testfx.api.FxAssert.verifyThat;

public class MainFxRmCommandTest  extends AbstractMainGUITest  {

    @Test
    public void testRmCommand() {
        step("Test command rm", () -> {
            interact(() -> {
                TextField commandTextField = lookup("#commandTextField").query();
                commandTextField.setText("mkdir A");
            });
            sleep(SLEEP_INTERVAL);
            interact(() -> type(ENTER));
            verifyThat("#commandTextField", TextInputControlMatchers.hasText(""));
            String a = String.format(Localization.getSingleton().localize("command.mkdir")) +"A\n";
            verifyThat("#outputArea", TextInputControlMatchers.hasText(a));


            interact(() -> {
                TextField commandTextField = lookup("#commandTextField").query();
                commandTextField.setText("rm A");
            });
            sleep(SLEEP_INTERVAL);
            interact(() -> type(ENTER));
            verifyThat("#commandTextField", TextInputControlMatchers.hasText(""));
            String b = String.format(Localization.getSingleton().localize("command.rm.remove.success")) +"A\n";
            verifyThat("#outputArea", TextInputControlMatchers.hasText(a+b));


            interact(() -> {
                TextField commandTextField = lookup("#commandTextField").query();
                commandTextField.setText("mkdir A");
            });
            sleep(SLEEP_INTERVAL);
            interact(() -> type(ENTER));
            verifyThat("#commandTextField", TextInputControlMatchers.hasText(""));
            String c = String.format(Localization.getSingleton().localize("command.mkdir")) +"A\n";
            verifyThat("#outputArea", TextInputControlMatchers.hasText(a+b+c));


            interact(() -> {
                TextField commandTextField = lookup("#commandTextField").query();
                commandTextField.setText("cd A");
            });
            sleep(SLEEP_INTERVAL);
            interact(() -> type(ENTER));
            verifyThat("#commandTextField", TextInputControlMatchers.hasText(""));
            String d = "A\n";
            verifyThat("#outputArea", TextInputControlMatchers.hasText(a+b+c+d));

            interact(() -> {
                TextField commandTextField = lookup("#commandTextField").query();
                commandTextField.setText("rm /A");
            });
            sleep(SLEEP_INTERVAL);
            interact(() -> type(ENTER));
            verifyThat("#commandTextField", TextInputControlMatchers.hasText(""));
            String e = String.format(Localization.getSingleton().localize("command.rm.remove.failed")) +"\n";
            verifyThat("#outputArea", TextInputControlMatchers.hasText(a+b+c+d+e));

            interact(() -> {
                TextField commandTextField = lookup("#commandTextField").query();
                commandTextField.setText("rm /");
            });
            sleep(SLEEP_INTERVAL);
            interact(() -> type(ENTER));
            verifyThat("#commandTextField", TextInputControlMatchers.hasText(""));
            String f = String.format(Localization.getSingleton().localize("command.rm.remove.root")) +"\n";
            verifyThat("#outputArea", TextInputControlMatchers.hasText(a+b+c+d+e+f));
        });
    }
}
