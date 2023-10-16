package ch.supsi.fsci.client.model;
import ch.supsi.fsci.client.controller.CommandExecutionController;
import ch.supsi.fsci.engine.FileSystemModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/* TODO: Complete the tests once all commands have been implemented! */

public class CommandLineModelTest {
    @Test
    public void testSetText() {
        FileSystemModel fileSystemModel = new FileSystemModel();
        CommandExecutionController commandExecutionController =
                new CommandExecutionController(fileSystemModel);
        commandExecutionController.initializeAllCommands("ch.supsi.fsci.engine.CommandPattern.Commands");
        final CommandLineModel commandLineModel = new CommandLineModel(fileSystemModel, commandExecutionController);
        assertEquals(commandLineModel.setText("pwd"), "Current working directory: \\");
        assertEquals(commandLineModel.setText("mkdir test"), "new directory created: test");
        assertEquals(commandLineModel.setText("ls"), "test ");
        assertEquals(commandLineModel.setText("cd \\A"), "The directory A has not been found with the [A] path");
        assertEquals(commandLineModel.setText("mv test test1"), "");
        assertEquals(commandLineModel.setText("rm test"), "cannot remove root directory");
        assertEquals(commandLineModel.setText("help"), "ls (list directory content): ls \n" +
                "mkdir (make directory): mkdir <dir name> \n" +
                "pwd (print working directory): pwd \n" +
                "cd (current directory): cd \n" +
                "mv (move): mv <origin> <destination> \n" +
                "rm (remove): rm <path> \n" +
                "clear: clears the previous outputs");
    }
}
