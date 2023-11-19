package ch.supsi.fsci.engine.CommandPattern.Commands;
import ch.supsi.fsci.engine.CommandPattern.CommandInfo;
import ch.supsi.fsci.engine.CommandPattern.CommandInterface;
import ch.supsi.fsci.engine.Controller.FileSystemController;
import ch.supsi.fsci.engine.Interface.FileSystemInterface;
import ch.supsi.fsci.engine.Model.FileSystemModel;

import java.util.StringTokenizer;

@CommandInfo(name = "cd", totalArguments = 1, commandSyntax = "cd <path>")
public class CdCommand implements CommandInterface {
    private final FileSystemController receiver;
    private final String path;

    public CdCommand(final FileSystemController fileSystemController, final StringTokenizer arguments) {
        this.path = arguments.nextToken();
        this.receiver = fileSystemController;
    }

    @Override
    public String execute() {
       return receiver.cd(path).getName();
    }
}
