package ch.supsi.fsci.client.view;

import javafx.scene.control.TextField;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandLineViewTest extends ApplicationTest {

    @Test
    public void testClearText() {
        CommandLineView commandLineView = new CommandLineView();
        TextField textField = new TextField();
        textField.setText("Prov");
        commandLineView.clearText(textField);
        assertEquals("", textField.getText());
    }
}
