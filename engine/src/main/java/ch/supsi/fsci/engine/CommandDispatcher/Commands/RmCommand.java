package ch.supsi.fsci.engine.CommandDispatcher.Commands;

import ch.supsi.fsci.engine.CommandDispatcher.CommandInfo;
import ch.supsi.fsci.engine.CommandDispatcher.CommandInterface;
import ch.supsi.fsci.engine.Model.FileSystemModel;

@CommandInfo(name = "rm", totalArguments = 1)
public class RmCommand implements CommandInterface {
    private final FileSystemModel receiver;
    private final String path;

    public RmCommand(final FileSystemModel receiver, String... arguments) {
        this.receiver = receiver;
        this.path = arguments[0];
    }

    @Override
    public String execute() {
        return receiver.rm(path);
    }
}
