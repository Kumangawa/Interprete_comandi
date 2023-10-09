package ch.supsi.fsci.engine.CommandPattern.Commands;

import ch.supsi.fsci.engine.CommandPattern.CommandInfo;
import ch.supsi.fsci.engine.CommandPattern.CommandInterface;
import ch.supsi.fsci.engine.FileSystemModel;

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
