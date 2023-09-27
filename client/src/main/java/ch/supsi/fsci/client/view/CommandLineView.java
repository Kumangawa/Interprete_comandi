package ch.supsi.fsci.client.view;

import javafx.scene.control.TextField;

public class CommandLineView implements CommandLineViewInterface{

    public void clearText(TextField textField) {
        textField.clear();
    }
}
