package ch.supsi.fsci.client.model;
import ch.supsi.fsci.client.controller.CommandExecutionController;
import ch.supsi.fsci.engine.Model.FileSystemModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandLineModelTest {
    @Test
    public void testSetText() {
        FileSystemModel fileSystemModel = new FileSystemModel();
        CommandExecutionController commandExecutionController =
                new CommandExecutionController(fileSystemModel, "ch.supsi.fsci.engine.CommandDispatcher.Commands");
        final CommandLineModel commandLineModel = new CommandLineModel(fileSystemModel, commandExecutionController);
        assertEquals(commandLineModel.setText("pwd"), "");
        assertEquals(commandLineModel.setText("mkdir test"), "");
        assertEquals(commandLineModel.setText("ls"), "");
        assertEquals(commandLineModel.setText("cd \\A"), "A");
        assertEquals(commandLineModel.setText("mv test test1"), "");
        assertEquals(commandLineModel.setText("rm test"), "");
        assertEquals(commandLineModel.setText("help"), "");
    }
}
