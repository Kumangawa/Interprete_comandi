package ch.supsi.fsci.engine.CommandPattern.Commands;

import ch.supsi.fsci.engine.CommandPattern.CommandInfo;
import ch.supsi.fsci.engine.CommandPattern.CommandInterface;
import ch.supsi.fsci.engine.Controller.FileSystemController;
import ch.supsi.fsci.engine.Interface.FileSystemInterface;
import ch.supsi.fsci.engine.Response;

@CommandInfo(name = "ls", totalArguments = 0, commandSyntax = "ls")
public class LsCommand implements CommandInterface {
    private final FileSystemController receiver;

    public LsCommand(final FileSystemController receiver) {
        this.receiver = receiver;
    }

    @Override
    public Response execute() {
        return receiver.ls();
    }
}
