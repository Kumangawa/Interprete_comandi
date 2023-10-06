package ch.supsi.fsci.engine.CommandPattern.Commands;

import ch.supsi.fsci.engine.CommandPattern.CommandInfo;
import ch.supsi.fsci.engine.CommandPattern.CommandInterface;
import ch.supsi.fsci.engine.Model.FileSystemModel;

import java.util.StringTokenizer;

@CommandInfo(name = "mkdir", totalArguments = 1)
public class MkdirCommand implements CommandInterface {
    private final FileSystemModel receiver;
    private final String directoryName;

    public MkdirCommand(final FileSystemModel receiver, final StringTokenizer arguments) {
        this.receiver = receiver;
        this.directoryName = arguments.nextToken();
    }

    @Override
    public String execute() {
        return receiver.mkdir(directoryName);
    }
}
