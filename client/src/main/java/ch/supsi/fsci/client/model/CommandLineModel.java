package ch.supsi.fsci.client.model;



import ch.supsi.fsci.engine.CommandDispatcher.CommandExecutionController;
import ch.supsi.fsci.engine.CommandDispatcher.CommandInterface;
import ch.supsi.fsci.engine.Model.FileSystemModel;

import java.util.Arrays;

public class CommandLineModel implements CommandLineModelInterface {
    final private FileSystemModel fileSystem;
    final private CommandExecutionController commandExecutionController;

    public CommandLineModel(final FileSystemModel fileSystem, final CommandExecutionController commandExecutionController) {
        this.fileSystem = fileSystem;
        this.commandExecutionController = commandExecutionController;
    }

    @Override
    public String setText(String text) {
        text = text.trim();
        final String[] commandParts = text.split("\\s+");

        try {
            final CommandInterface command = commandExecutionController.getDispatchedCommand(commandParts[0],
                    Arrays.copyOfRange(commandParts, 1, commandParts.length));
            return command.execute();
        }
        catch (final Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
