package ch.supsi.fsci.engine.CommandPattern.Commands;
import ch.supsi.fsci.engine.CommandPattern.CommandInfo;
import ch.supsi.fsci.engine.CommandPattern.CommandInterface;
import ch.supsi.fsci.engine.FileSystemModel;

import java.util.StringTokenizer;

@CommandInfo(name = "cd", totalArguments = 1)
public class CdCommand implements CommandInterface {
    private final FileSystemModel receiver;
    private final String path;

    public CdCommand(final FileSystemModel fileSystemModel, final StringTokenizer arguments) {
        this.path = arguments.nextToken();
        this.receiver = fileSystemModel;
    }

    @Override
    public String execute() {
       return receiver.cd(path);
    }
}
