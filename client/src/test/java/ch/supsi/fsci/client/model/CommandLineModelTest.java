package ch.supsi.fsci.client.model;
import ch.supsi.fsci.engine.Model.FileSystemModel;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandLineModelTest {
    @Test
    public void testSetText() {
        final CommandLineModel commandLineModel = new CommandLineModel(new FileSystemModel());
        assertEquals(commandLineModel.setText("cdd should be an error"), "error");
        assertEquals(commandLineModel.setText("pwd"), "");
        assertEquals(commandLineModel.setText("mkdir test test2"), "");
        assertEquals(commandLineModel.setText("ls"), "");
        assertEquals(commandLineModel.setText("cd \\A"), "changed directory to: A");
        assertEquals(commandLineModel.setText("mv test test1"), "");
        assertEquals(commandLineModel.setText("rm test"), "");
        assertEquals(commandLineModel.setText("help"), "");
        assertEquals(commandLineModel.setText("clear"), "");
    }
}
