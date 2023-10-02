package ch.supsi.fsci.client.view;

import javafx.scene.control.TextArea;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class OutputAreaViewTest extends ApplicationTest {

    @Test
    public void testAddText(){
        TextArea textArea = new TextArea();
        OutputAreaView outputAreaView = new OutputAreaView(textArea);
        outputAreaView.addText("Prova");
        assertEquals("Prova\n", textArea.getText());
    }

    @Test
    public void testClearOutputArea() {
        TextArea textArea = new TextArea();
        OutputAreaView outputAreaView = new OutputAreaView(textArea);
        textArea.setText("Prova");
        outputAreaView.clearOutputArea();
        assertEquals("", textArea.getText());
    }
}
