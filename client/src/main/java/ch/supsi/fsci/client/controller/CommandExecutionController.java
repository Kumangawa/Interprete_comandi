package ch.supsi.fsci.client.controller;

import ch.supsi.fsci.client.model.CommandExecutionModelInterface;
import ch.supsi.fsci.engine.CommandPattern.CommandInterface;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CommandExecutionController {

    private final CommandExecutionModelInterface commandExecutionModel;
    private TextField textField;
    private TextArea outputArea;

    public CommandExecutionController(TextField textField, TextArea outputArea, CommandExecutionModelInterface commandExecutionModel) {
        this.textField = textField;
        this.outputArea = outputArea;
        this.commandExecutionModel = commandExecutionModel;
    }

    public void initialize(){
        // EventHandler for the "Enter" key in the TextField
        textField.setOnAction(event -> {
            // Get the entered text
            String input = textField.getText();
            String output = elaborateText(input);
            // Clear the text field
            textField.clear();
            // Add the text in the output area
            if (output.equals("clear")) {
                outputArea.clear();
            } else {
                outputArea.appendText(output+'\n');
            }
        });
    }

    private String elaborateText(final String input) {
        try {
            final CommandInterface command = commandExecutionModel.getDispatchedCommand(input);
            return command.execute();
        }
        catch (final Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
