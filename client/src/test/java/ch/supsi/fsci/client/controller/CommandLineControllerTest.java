package ch.supsi.fsci.client.controller;

import ch.supsi.fsci.client.model.CommandLineModel;
import ch.supsi.fsci.client.view.CommandLineView;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import static org.mockito.Mockito.*;

public class CommandLineControllerTest extends ApplicationTest {

    /**
     * A mock is a simulated object used in testing to emulate the behavior of a real component.
     * It is used to isolate the component under test, define its behavior,
     * and verify interactions with it.
     */
    @Test
    public void testInitialize() {
        // Create a mock for CommandLineModel, CommandLineView
        CommandLineModel commandLineModel = mock(CommandLineModel.class);
        CommandLineView commandLineView = mock(CommandLineView.class);
        TextField textField = new TextField("Test Input");
        CommandLineController commandLineController = new CommandLineController(textField, commandLineModel, commandLineView);

        commandLineController.inizialize();

        // Simulate the user action of pressing the "Enter" key in the TextField.
        // This should trigger the EventHandler defined in your controller.
        interact(() -> {
            textField.fireEvent(new ActionEvent());
        });

        // Verify that the setText and clearText methods were called.
        verify(commandLineModel, atLeastOnce()).setText("Test Input");
        verify(commandLineView, atLeastOnce()).clearText(any(TextField.class));
    }
}
