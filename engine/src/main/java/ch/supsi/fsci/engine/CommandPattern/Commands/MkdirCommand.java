package ch.supsi.fsci.engine.CommandPattern.Commands;

import ch.supsi.fsci.engine.CommandPattern.CommandInfo;
import ch.supsi.fsci.engine.CommandPattern.CommandInterface;
import ch.supsi.fsci.engine.Controller.FileSystemController;
import ch.supsi.fsci.engine.Interface.FileSystemInterface;
import ch.supsi.fsci.engine.Response;

import java.util.StringTokenizer;

@CommandInfo(name = "mkdir", totalArguments = 1, commandSyntax = "mkdir <directory>")
public class MkdirCommand implements CommandInterface {
    private final FileSystemController receiver;
    private final String directoryName;

    public MkdirCommand(final FileSystemController receiver, final StringTokenizer arguments) {
        this.receiver = receiver;
        this.directoryName = arguments.nextToken();
    }

    @Override
    public Response execute() {
        return receiver.mkdir(directoryName);
    }
}
