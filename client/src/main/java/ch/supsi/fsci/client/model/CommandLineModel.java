package ch.supsi.fsci.client.model;



import ch.supsi.fsci.client.controller.CommandExecutionController;
import ch.supsi.fsci.engine.CommandPattern.CommandInterface;
import ch.supsi.fsci.engine.FileSystemModel;

public class CommandLineModel implements CommandLineModelInterface {
    final private FileSystemModel fileSystem;
    final private CommandExecutionController commandExecutionController;

    public CommandLineModel(final FileSystemModel fileSystem, final CommandExecutionController commandExecutionController) {
        this.fileSystem = fileSystem;
        this.commandExecutionController = commandExecutionController;
    }

    @Override
    public String setText(final String input) {
        try {
            final CommandInterface command = commandExecutionController.getDispatchedCommand(input);
            return command.execute();
        }
        catch (final Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
