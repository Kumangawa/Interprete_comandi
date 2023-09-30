package ch.supsi.fsci.client.view;

import javafx.scene.control.TextField;

public class CommandLineView implements CommandLineViewInterface {

    @Override
    public void clearText(TextField textField) {
        textField.clear();
    }
}
