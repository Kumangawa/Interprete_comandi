package ch.supsi.fsci.client.controller;

import ch.supsi.fsci.client.model.CommandExecutionModelInterface;
import ch.supsi.fsci.engine.CommandPattern.CommandInterface;
import ch.supsi.fsci.engine.Exceptions.ApplicationBaseException;
import ch.supsi.fsci.engine.Exceptions.DirectoryNotFound;
import ch.supsi.fsci.engine.Exceptions.WrongCommandArgumentNumberException;
import ch.supsi.fsci.engine.Response;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class CommandExecutionController {

    private final CommandExecutionModelInterface commandExecutionModel;
    private TextField textField;
    private TextArea outputArea;

    public CommandExecutionController(TextField textField, TextArea outputArea, CommandExecutionModelInterface commandExecutionModel) {
        this.textField = textField;
        this.outputArea = outputArea;
        this.commandExecutionModel = commandExecutionModel;
    }

    public void initialize() {
        textField.setOnAction(event -> {
            final String input = textField.getText();
            if (input.equals("clear") || input.startsWith("clear ")) {
                outputArea.clear();
                textField.clear();
                return;
            }
            Response output = null;
            try {
                output = elaborateText(input);
            } catch (Exception e) {
                System.out.println(Arrays.toString(e.getStackTrace()));
            }
            textField.clear();

            assert output != null;
            outputArea.appendText(output.localize() + '\n');
        });
    }

    private Response elaborateText(final String input) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        try {
            final CommandInterface command = commandExecutionModel.getDispatchedCommand(input);
            return command.execute();
        }
        catch (final ApplicationBaseException e) {
            return new Response(e.getKey(), e.getAdditionalParameters());
        }

    }
}
