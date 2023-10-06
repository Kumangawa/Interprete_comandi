package ch.supsi.fsci.engine.CommandPattern.Commands;

import ch.supsi.fsci.engine.CommandPattern.CommandInfo;
import ch.supsi.fsci.engine.CommandPattern.CommandInterface;
import ch.supsi.fsci.engine.Model.FileSystemModel;

import java.util.StringTokenizer;

@CommandInfo(name = "rm", totalArguments = 1)
public class RmCommand implements CommandInterface {
    private final FileSystemModel receiver;
    private final String path;

    public RmCommand(final FileSystemModel receiver, final StringTokenizer arguments) {
        this.receiver = receiver;
        this.path = arguments.nextToken();
    }

    @Override
    public String execute() {
        return receiver.rm(path);
    }
}
