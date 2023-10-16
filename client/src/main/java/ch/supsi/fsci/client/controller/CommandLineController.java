package ch.supsi.fsci.client.controller;

import ch.supsi.fsci.client.model.CommandLineModelInterface;
import ch.supsi.fsci.client.view.CommandLineViewInterface;
import ch.supsi.fsci.client.view.OutputAreaViewInterface;
import javafx.scene.control.TextField;

import java.util.Locale;

public class CommandLineController {
    private TextField textField;
    private CommandLineModelInterface commandLineModel;
    private CommandLineViewInterface commandLineView;
    private OutputAreaViewInterface outputAreaView;

    public CommandLineController(TextField textField, CommandLineModelInterface commandLineModel, CommandLineViewInterface commandLineView,
                                 OutputAreaViewInterface outputAreaView) {
        this.textField = textField;
        this.commandLineModel = commandLineModel;
        this.commandLineView = commandLineView;
        this.outputAreaView = outputAreaView;
    }

    public void initialize() {
        // EventHandler for the "Enter" key in the TextField
        textField.setOnAction(event -> {
            // Get the entered text
            String input = textField.getText();
            String output = commandLineModel.setText(input);
            // Clear the text field
            commandLineView.clearText(textField);
            // Add the text in the output area
            outputAreaView.addText(output);
            //outputAreaView.clearOutputArea();
        });
    }

}
