package ch.supsi.fsci.engine.CommandDispatcher.Commands;

import ch.supsi.fsci.engine.CommandDispatcher.CommandInfo;
import ch.supsi.fsci.engine.CommandDispatcher.CommandInterface;
import ch.supsi.fsci.engine.Model.FileSystemModel;

@CommandInfo(name = "mkdir", totalArguments = 1)
public class MkdirCommand implements CommandInterface {
    private final FileSystemModel receiver;
    private final String directoryName;

    public MkdirCommand(final FileSystemModel receiver, String... arguments) {
        this.receiver = receiver;
        this.directoryName = arguments[0];
    }

    @Override
    public String execute() {
        return receiver.mkdir(directoryName);
    }
}
