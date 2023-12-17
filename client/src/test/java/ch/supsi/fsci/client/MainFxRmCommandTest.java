package ch.supsi.fsci.client;

import ch.supsi.fsci.engine.Localization;
import ch.supsi.fsci.engine.Model.FileSystemModel;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.Test;
import org.testfx.matcher.control.TextInputControlMatchers;

import static javafx.scene.input.KeyCode.ENTER;
import static org.testfx.api.FxAssert.verifyThat;

public class MainFxRmCommandTest  extends AbstractMainGUITest  {

    @Test
    public void
    testRmCommand() {
        FileSystemModel fileSystemModel = new FileSystemModel();
        String separator = fileSystemModel.getSeparator();
        String lettera = "A";
        String a = String.format(Localization.getSingleton().localize("command.mkdir"), lettera)+"\n";
        String b = String.format(Localization.getSingleton().localize("command.rm.success"), lettera) + "\n";
        String c = String.format(Localization.getSingleton().localize("command.mkdir"), lettera) +"\n";
        String d = String.format(Localization.getSingleton().localize("command.cd.success"), lettera) +"\n";
        String e = String.format(Localization.getSingleton().localize("command.rm.failed")) +"\n";
        String f = String.format(Localization.getSingleton().localize("command.rm.root"), separator) +"\n";
        step("Test command rm, create " + lettera + " and then remove it", () -> {
            interact(() -> {
                TextField commandTextField = lookup("#commandTextField").query();
                commandTextField.setText("mkdir " + lettera);
            });
            sleep(SLEEP_INTERVAL);
            interact(() -> type(ENTER));
            verifyThat("#commandTextField", TextInputControlMatchers.hasText(""));
            verifyThat("#outputArea", TextInputControlMatchers.hasText(a));


            interact(() -> {
                TextField commandTextField = lookup("#commandTextField").query();
                commandTextField.setText("rm " + lettera);
            });
            sleep(SLEEP_INTERVAL);
            interact(() -> type(ENTER));
            verifyThat("#commandTextField", TextInputControlMatchers.hasText(""));
            verifyThat("#outputArea", TextInputControlMatchers.hasText(a + b));
        });

        step("Test command rm, create " + lettera + " move in it, and the try to remove it", () -> {
            interact(() -> {
                TextField commandTextField = lookup("#commandTextField").query();
                commandTextField.setText("mkdir "+lettera);
            });
            sleep(SLEEP_INTERVAL);
            interact(() -> type(ENTER));
            verifyThat("#commandTextField", TextInputControlMatchers.hasText(""));
            verifyThat("#outputArea", TextInputControlMatchers.hasText(a+b+c));

            interact(() -> {
                TextField commandTextField = lookup("#commandTextField").query();
                commandTextField.setText("cd "+lettera);
            });
            sleep(SLEEP_INTERVAL);
            interact(() -> type(ENTER));
            verifyThat("#commandTextField", TextInputControlMatchers.hasText(""));
            verifyThat("#outputArea", TextInputControlMatchers.hasText(a+b+c+d));

            interact(() -> {
                TextField commandTextField = lookup("#commandTextField").query();
                commandTextField.setText("rm "+ separator+lettera);
            });
            sleep(SLEEP_INTERVAL);
            interact(() -> type(ENTER));
            verifyThat("#commandTextField", TextInputControlMatchers.hasText(""));
            verifyThat("#outputArea", TextInputControlMatchers.hasText(a+b+c+d+e));
        });

        step("Test command rm, try to remove root", () -> {
            interact(() -> {
                TextField commandTextField = lookup("#commandTextField").query();
                commandTextField.setText("rm "+separator);
            });
            sleep(SLEEP_INTERVAL);
            interact(() -> type(ENTER));
            verifyThat("#commandTextField", TextInputControlMatchers.hasText(""));
            verifyThat("#outputArea", TextInputControlMatchers.hasText(a+b+c+d+e+f));
        });
    }
}
