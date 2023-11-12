package ch.supsi.fsci.client;

import ch.supsi.fsci.engine.Localization;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.Test;
import org.testfx.matcher.control.TextInputControlMatchers;

import static javafx.scene.input.KeyCode.ENTER;
import static org.testfx.api.FxAssert.verifyThat;

public class MainFxMvCommandTest  extends AbstractMainGUITest {

    @Test
    public void testMvCommand() {
        step("Test command mv", () -> {
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
                commandTextField.setText("mkdir B");
            });
            sleep(SLEEP_INTERVAL);
            interact(() -> type(ENTER));
            verifyThat("#commandTextField", TextInputControlMatchers.hasText(""));
            String b = String.format(Localization.getSingleton().localize("command.mkdir")) +"B\n";
            verifyThat("#outputArea", TextInputControlMatchers.hasText(a + b));


            interact(() -> {
                TextField commandTextField = lookup("#commandTextField").query();
                commandTextField.setText("mv A B");
            });

            sleep(SLEEP_INTERVAL);
            interact(() -> type(ENTER));
            verifyThat("#commandTextField", TextInputControlMatchers.hasText(""));
            // TODO: da mettere a posto le risposte del comando mv, al momento è arcodato
            String c = "Impossibile spostare la directory di origine.\n";
            verifyThat("#outputArea", TextInputControlMatchers.hasText(a+b+c));
        });
    }
}
