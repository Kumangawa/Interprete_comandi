package ch.supsi.fsci.client.view;

import javafx.scene.control.TextArea;

public class OutputAreaView implements OutputAreaViewInterface{
    private TextArea textArea;

    public OutputAreaView(TextArea textArea) {
        this.textArea = textArea;
    }


    @Override
    public void addText(String output) {
        textArea.appendText(output+'\n');
    }

    @Override
    public void clearOutputArea() {
        textArea.clear();
    }
}
