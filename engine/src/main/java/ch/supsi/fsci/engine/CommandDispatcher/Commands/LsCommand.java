package ch.supsi.fsci.engine.CommandDispatcher.Commands;

import ch.supsi.fsci.engine.CommandDispatcher.CommandInfo;
import ch.supsi.fsci.engine.CommandDispatcher.CommandInterface;
import ch.supsi.fsci.engine.Model.FileSystemModel;

@CommandInfo(name = "ls", totalArguments = 0)
public class LsCommand implements CommandInterface {
    private final FileSystemModel receiver;

    public LsCommand(final FileSystemModel receiver) {
        this.receiver = receiver;
    }

    @Override
    public String execute() {
        return receiver.ls();
    }
}
