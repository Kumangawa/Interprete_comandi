package ch.supsi.fsci.engine.CommandPattern.Commands;

import ch.supsi.fsci.engine.CommandPattern.CommandInfo;
import ch.supsi.fsci.engine.CommandPattern.CommandInterface;
import ch.supsi.fsci.engine.Interface.FileSystemInterface;

import java.util.StringTokenizer;

@CommandInfo(name = "mkdir", totalArguments = 1, commandSyntax = "mkdir <directory>")
public class MkdirCommand implements CommandInterface {
    private final FileSystemInterface receiver;
    private final String directoryName;

    public MkdirCommand(final FileSystemInterface receiver, final StringTokenizer arguments) {
        this.receiver = receiver;
        this.directoryName = arguments.nextToken();
    }

    @Override
    public String execute() {
        return receiver.mkdir(directoryName);
    }
}
