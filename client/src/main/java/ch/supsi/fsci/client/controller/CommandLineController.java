package ch.supsi.fsci.client.controller;

import ch.supsi.fsci.client.model.CommandLineModelInterface;
import ch.supsi.fsci.client.view.CommandLineViewInterface;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CommandLineController {
    private TextField textField;
    private CommandLineModelInterface commandLineModel;
    private CommandLineViewInterface commandLineView;

    // TODO: Separate this into different controller?
    private TextArea textArea;

    public CommandLineController(TextField textField, CommandLineModelInterface commandLineModel, CommandLineViewInterface commandLineView,
                                 TextArea textArea) {
        this.textField = textField;
        this.textArea = textArea;
        this.commandLineModel = commandLineModel;
        this.commandLineView = commandLineView;
    }

    public void initialize() {
        // EventHandler for the "Enter" key in the TextField
        textField.setOnAction(event -> {
            // Get the entered text
            String input = textField.getText();
            final String output = commandLineModel.setText(input);
            // Clear the text field
            commandLineView.clearText(textField);

            textArea.appendText(output+'\n');
        });
    }

}
