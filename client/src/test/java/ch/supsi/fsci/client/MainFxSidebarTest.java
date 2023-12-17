package ch.supsi.fsci.client;
import ch.supsi.fsci.engine.Localization;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;

import static javafx.scene.input.KeyCode.ENTER;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainFxSidebarTest extends AbstractMainGUITest {

    @Test
    public void testSidebarAppearance() {
        String a = String.format(Localization.getSingleton().localize("command.help")) + "\n";
        step("Test sidebar appearance", () -> {
            interact(() -> {
                TextField commandTextField = lookup("#commandTextField").query();
                commandTextField.setText("help");
            });
            sleep(SLEEP_INTERVAL);
            interact(() -> type(ENTER));
            interact(() -> {
                TextField commandTextField = lookup("#commandTextField").query();
                commandTextField.setText("help");
            });
            sleep(SLEEP_INTERVAL);
            interact(() -> type(ENTER));
            interact(() -> {
                TextField commandTextField = lookup("#commandTextField").query();
                commandTextField.setText("help");
            });
            sleep(SLEEP_INTERVAL);
            interact(() -> type(ENTER));

            interact(() -> {
                TextField commandTextField = lookup("#commandTextField").query();
                commandTextField.setText("help");
            });
            interact(() -> type(ENTER));

            // Verify that the vertical scrollbar is visible in the outputArea
            FxRobot fxRobot = new FxRobot();
            TextArea outputArea = lookup("#outputArea").query();
            assertTrue(outputArea.lookup(".scroll-bar:vertical").isVisible(), "Vertical scrollbar is not visible");
        });
    }
}
