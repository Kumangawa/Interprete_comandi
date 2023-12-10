package ch.supsi.fsci.engine.CommandPattern.Commands;

import ch.supsi.fsci.engine.CommandPattern.CommandInfo;
import ch.supsi.fsci.engine.CommandPattern.CommandInterface;
import ch.supsi.fsci.engine.Controller.FileSystemController;
import ch.supsi.fsci.engine.Interface.FileSystemInterface;
import ch.supsi.fsci.engine.Response;

@CommandInfo(name = "pwd", totalArguments = 0, commandSyntax = "pwd")
public class PwdCommand implements CommandInterface {
    private final FileSystemController receiver;

    public PwdCommand(final FileSystemController receiver) {
        this.receiver = receiver;
    }

    @Override
    public Response execute() {
        return receiver.pwd();
    }
}
