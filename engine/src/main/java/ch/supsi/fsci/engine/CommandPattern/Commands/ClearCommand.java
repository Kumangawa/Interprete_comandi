package ch.supsi.fsci.engine.CommandPattern.Commands;

import ch.supsi.fsci.engine.CommandPattern.CommandInfo;
import ch.supsi.fsci.engine.CommandPattern.CommandInterface;
import ch.supsi.fsci.engine.FileSystemModel;

@CommandInfo(name = "clear", totalArguments = 0, commandSyntax = "clear")
public class ClearCommand implements CommandInterface {
    private final FileSystemModel fileSystemModel;

    public ClearCommand(FileSystemModel fileSystemModel) {
        this.fileSystemModel = fileSystemModel;
    }

    @Override
    public String execute() {
        return fileSystemModel.clear();
    }
}
