package ch.supsi.fsci.engine.CommandPattern.Commands;

import ch.supsi.fsci.engine.CommandPattern.CommandInfo;
import ch.supsi.fsci.engine.CommandPattern.CommandInterface;
import ch.supsi.fsci.engine.Controller.FileSystemController;
import ch.supsi.fsci.engine.Interface.FileSystemInterface;
import ch.supsi.fsci.engine.Response;

import java.util.StringTokenizer;

@CommandInfo(name = "rm", totalArguments = 1, commandSyntax = "rm <path>")
public class RmCommand implements CommandInterface {
    private final FileSystemController receiver;
    private final String path;

    public RmCommand(final FileSystemController receiver, final StringTokenizer arguments) {
        this.receiver = receiver;
        this.path = arguments.nextToken();
    }

    @Override
    public Response execute() {
        return receiver.rm(path);
    }
}
