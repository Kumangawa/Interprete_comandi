package ch.supsi.fsci.client.controller;

import ch.supsi.fsci.client.model.CommandExecutionModelInterface;
import ch.supsi.fsci.engine.CommandPattern.CommandInterface;
import ch.supsi.fsci.engine.Response;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class CommandExecutionControllerTest extends ApplicationTest  {

    private CommandExecutionModelInterface commandExecutionModel;
    private TextField textField;
    private TextArea outputArea;
    private CommandExecutionController commandExecutionController;

    @BeforeEach
    public void setUp() {
        commandExecutionModel = Mockito.mock(CommandExecutionModelInterface.class);
        textField = new TextField();
        outputArea = new TextArea();
        commandExecutionController = new CommandExecutionController(textField, outputArea, commandExecutionModel);
        commandExecutionController.initialize();
    }

    @Test
    public void testElaborateText() throws Exception {
        String input = "test input";
        String expectedOutput = "test output";

        CommandInterface mockCommand = Mockito.mock(CommandInterface.class);
        when(commandExecutionModel.getDispatchedCommand(input)).thenReturn(mockCommand);
        when(mockCommand.execute()).thenReturn(new Response("test output"));

        textField.setText(input);
        textField.fireEvent(new javafx.event.ActionEvent());

        assertEquals("", textField.getText());
        assertEquals(expectedOutput + '\n', outputArea.getText());
    }
}
