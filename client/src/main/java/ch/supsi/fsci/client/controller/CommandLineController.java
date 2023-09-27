package ch.supsi.fsci.client.controller;

import ch.supsi.fsci.client.model.CommandLineModelInterface;
import ch.supsi.fsci.client.view.CommandLineViewInterface;
import javafx.scene.control.TextField;

public class CommandLineController {
    private TextField textField;
    private CommandLineModelInterface commandLineModel;
    private CommandLineViewInterface commandLineView;

    public CommandLineController(TextField textField, CommandLineModelInterface commandLineModel, CommandLineViewInterface commandLineView) {
        this.textField = textField;
        this.commandLineModel = commandLineModel;
        this.commandLineView = commandLineView;
    }

    public void inizialize(){
        // EventHandler for the "Enter" key in the TextField
        textField.setOnAction(event -> {
            // Get the entered text
            String input = textField.getText();
            commandLineModel.setText(input);
            // Clear the text field
            commandLineView.clearText(textField);
        });
    }

}
