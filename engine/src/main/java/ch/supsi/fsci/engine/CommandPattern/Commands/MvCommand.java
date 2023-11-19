package ch.supsi.fsci.engine.CommandPattern.Commands;

import ch.supsi.fsci.engine.CommandPattern.CommandInfo;
import ch.supsi.fsci.engine.CommandPattern.CommandInterface;
import ch.supsi.fsci.engine.Controller.FileSystemController;
import ch.supsi.fsci.engine.Interface.FileSystemInterface;

import java.util.StringTokenizer;

@CommandInfo(name = "mv", totalArguments = 2, commandSyntax = "mv <src> <dest>")
public class MvCommand implements CommandInterface {
    private final FileSystemController receiver;
    private final String origin;
    private final String destination;

    public MvCommand(final FileSystemController receiver, final StringTokenizer arguments) {
        this.receiver = receiver;
        this.origin = arguments.nextToken();
        this.destination = arguments.nextToken();
    }

    @Override
    public String execute() {
        return receiver.mv(origin, destination);
    }
}
