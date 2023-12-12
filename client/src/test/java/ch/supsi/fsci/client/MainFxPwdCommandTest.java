package ch.supsi.fsci.client;

import ch.supsi.fsci.engine.Localization;
import ch.supsi.fsci.engine.Model.FileSystemModel;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.Test;
import org.testfx.matcher.control.TextInputControlMatchers;

import static javafx.scene.input.KeyCode.ENTER;
import static org.testfx.api.FxAssert.verifyThat;

public class MainFxPwdCommandTest  extends AbstractMainGUITest {

    @Test
    public void testPwdCommand() {
        FileSystemModel fileSystemModel = new FileSystemModel();
        String a = String.format(Localization.getSingleton().localize("command.pwd"), fileSystemModel.getSeparator()) + "\n";

        step("Test command pwd in root", () -> {
            interact(() -> {
                TextField commandTextField = lookup("#commandTextField").query();
                commandTextField.setText("pwd");
            });
            sleep(SLEEP_INTERVAL);
            interact(() -> type(ENTER));
            verifyThat("#commandTextField", TextInputControlMatchers.hasText(""));
            verifyThat("#outputArea", TextInputControlMatchers.hasText(a));
        });


        String lettera = "A";
        String b = String.format(Localization.getSingleton().localize("command.mkdir"), lettera) +"\n";
        String c = String.format(Localization.getSingleton().localize("command.cd.success"), lettera) +"\n";
        String d = String.format(Localization.getSingleton().localize("command.pwd"), fileSystemModel.getSeparator()+lettera) +"\n";

        step("Test command pwd in "+lettera, () -> {
            interact(() -> {
                TextField commandTextField = lookup("#commandTextField").query();
                commandTextField.setText("mkdir "+ lettera);
            });
            sleep(SLEEP_INTERVAL);
            interact(() -> type(ENTER));
            verifyThat("#commandTextField", TextInputControlMatchers.hasText(""));
            verifyThat("#outputArea", TextInputControlMatchers.hasText(a + b));


            interact(() -> {
                TextField commandTextField = lookup("#commandTextField").query();
                commandTextField.setText("cd "+lettera);
            });
            sleep(SLEEP_INTERVAL);
            interact(() -> type(ENTER));
            verifyThat("#commandTextField", TextInputControlMatchers.hasText(""));

            verifyThat("#outputArea", TextInputControlMatchers.hasText(a+b+c));


            interact(() -> {
                TextField commandTextField = lookup("#commandTextField").query();
                commandTextField.setText("pwd");
            });
            sleep(SLEEP_INTERVAL);
            interact(() -> type(ENTER));
            verifyThat("#commandTextField", TextInputControlMatchers.hasText(""));
            verifyThat("#outputArea", TextInputControlMatchers.hasText(a+b+c+d));
        });
    }
}
