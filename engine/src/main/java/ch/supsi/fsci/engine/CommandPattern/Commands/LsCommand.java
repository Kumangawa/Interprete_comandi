package ch.supsi.fsci.engine.CommandPattern.Commands;

import ch.supsi.fsci.engine.CommandPattern.CommandInfo;
import ch.supsi.fsci.engine.CommandPattern.CommandInterface;
import ch.supsi.fsci.engine.Interface.FileSystemInterface;

@CommandInfo(name = "ls", totalArguments = 0, commandSyntax = "ls")
public class LsCommand implements CommandInterface {
    private final FileSystemInterface receiver;

    public LsCommand(final FileSystemInterface receiver) {
        this.receiver = receiver;
    }

    @Override
    public String execute() {
        return receiver.ls();
    }
}
