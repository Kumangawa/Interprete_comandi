package ch.supsi.fsci.client;

import org.junit.jupiter.api.Test;
import org.testfx.matcher.control.TextInputControlMatchers;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

public class MainFx00Test extends AbstractMainGUITest {

    @Test
    public void walkThrough() {
        testMainScene();
    }

    private void testMainScene() {
        step("main scene", () -> {
            verifyThat("#commandLabel", isVisible());
            verifyThat("#commandTextField", isVisible());
            verifyThat("#outputArea", isVisible());

            verifyThat("#commandTextField", TextInputControlMatchers.hasText(""));
        });
    }

}
