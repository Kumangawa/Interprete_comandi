package ch.supsi.fsci.client.model;

import ch.supsi.fsci.engine.CommandPattern.CommandInterface;
import ch.supsi.fsci.engine.CommandPattern.Commands.*;
import ch.supsi.fsci.engine.Exceptions.WrongCommandArgumentNumberException;
import ch.supsi.fsci.engine.Exceptions.WrongCommandNameException;
import ch.supsi.fsci.engine.Model.FileSystemModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CommandExecutionModelTest {
    private CommandExecutionModel controller;

    @BeforeEach
    public void setUp() {
        controller = new CommandExecutionModel(new FileSystemModel());
    }

    @Test
    public void testInitializationOfCommandList() {
        assertTrue(controller.commandList.isEmpty());
    }

    @Test
    public void testInitializeAllCommandsWithValidPackage() {
        controller.initializeAllCommands("ch.supsi.fsci.engine.CommandPattern.Commands");

        assertTrue(controller.commandList.containsKey("cd"));
        assertTrue(controller.commandList.containsKey("help"));
        assertTrue(controller.commandList.containsKey("ls"));
        assertTrue(controller.commandList.containsKey("mkdir"));
        assertTrue(controller.commandList.containsKey("mv"));
        assertTrue(controller.commandList.containsKey("pwd"));
        assertTrue(controller.commandList.containsKey("rm"));

        assertEquals(CdCommand.class, controller.commandList.get("cd"));
        assertEquals(HelpCommand.class, controller.commandList.get("help"));
        assertEquals(LsCommand.class, controller.commandList.get("ls"));
        assertEquals(MkdirCommand.class, controller.commandList.get("mkdir"));
        assertEquals(MvCommand.class, controller.commandList.get("mv"));
        assertEquals(PwdCommand.class, controller.commandList.get("pwd"));
        assertEquals(RmCommand.class, controller.commandList.get("rm"));
    }


    @Test
    public void testInitializeAllCommandsWithInvalidPackage() {
        controller.initializeAllCommands("ch.supsi.fsci.engine.Exceptions");
        assertTrue(controller.commandList.isEmpty());
    }

    @Test
    public void testInitializeAllCommandsWithEmptyPackage() {
        assertThrows(Exception.class, () -> controller.initializeAllCommands(""));
        assertTrue(controller.commandList.isEmpty());
    }


    @Test
    public void testGetDispatchedCommandWithValidCommandAndArguments() throws Exception {
        controller.initializeAllCommands("ch.supsi.fsci.engine.CommandPattern.Commands");

        final String[] validCommands = { "mv file1 file2", "cd path", "ls", "mkdir dir", "pwd", "rm dir" };
        for (final String currentValidCommand: validCommands) {
            CommandInterface command = controller.getDispatchedCommand(currentValidCommand);
            assertNotNull(command);
        }
    }

    @Test
    public void testGetDispatchedCommandWithInvalidCommandName() {
        controller.initializeAllCommands("ch.supsi.fsci.engine.CommandPattern.Commands");

        final String[] invalidCommandNames = { "mve file1 file2", "cr path", "la", "mkdlr dir", "piwd", "rmm dir" };
        for (final String currentInvalidCommandName: invalidCommandNames) {
            assertThrows(WrongCommandNameException.class, () -> controller.getDispatchedCommand(currentInvalidCommandName));
        }
    }

    @Test
    public void testGetDispatchedCommandWithEmptyInput() {
        controller.initializeAllCommands("ch.supsi.fsci.engine.CommandPattern.Commands");

        final String input = "";
        assertThrows(IllegalArgumentException.class, () -> controller.getDispatchedCommand(input));
    }

    @Test
    public void testGetDispatchedCommandWithWrongNumberOfArguments() {
        controller.initializeAllCommands("ch.supsi.fsci.engine.CommandPattern.Commands");

        final String[] invalidCommandArgument = {
                "mv file1 file2 file3", "mv", "mv file1", "cd", "cd path0 path", "ls one",
                "mkdir dir dir2", "mkdir", "pwd one", "rm" };
        for (final String currentInvalidCommandArgument: invalidCommandArgument) {
            assertThrows(WrongCommandArgumentNumberException.class, () -> controller.getDispatchedCommand(currentInvalidCommandArgument));
        }
    }
}
