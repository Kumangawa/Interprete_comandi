package ch.supsi.fsci.client.controller;

import ch.supsi.fsci.client.model.CommandLineModel;
import ch.supsi.fsci.client.view.CommandLineView;
import ch.supsi.fsci.client.view.OutputAreaView;
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
        // Create a mock for OutputAreaView, CommandLineModel, CommandLineView
        OutputAreaView outputAreaView = mock(OutputAreaView.class);
        CommandLineView commandLineView = mock(CommandLineView.class);
        CommandLineModel commandLineModel = mock(CommandLineModel.class);
        TextField textField = new TextField("Test Input");

        // Set up the behavior of the CommandLineModel mock
        when(commandLineModel.setText(anyString())).thenReturn("Test Output");

        CommandLineController commandLineController = new CommandLineController(textField, commandLineModel,
                commandLineView, outputAreaView);

        commandLineController.initialize();

        // Simulate the user action of pressing the "Enter" key in the TextField.
        // This should trigger the EventHandler defined in your controller.
        interact(() -> {
            textField.fireEvent(new ActionEvent());
        });

        // Verify that the setText and clearText methods were called.
        verify(commandLineModel, atLeastOnce()).setText("Test Input");
        verify(commandLineView, atLeastOnce()).clearText(textField);
        // Verify that the addText method of the outputAreaView was called with the expected output
        verify(outputAreaView, atLeastOnce()).addText("Test Output");
    }
}
