package ch.supsi.fsci.engine.CommandPattern.Commands;

import ch.supsi.fsci.engine.CommandPattern.CommandInfo;
import ch.supsi.fsci.engine.CommandPattern.CommandInterface;
import ch.supsi.fsci.engine.Interface.FileSystemInterface;

@CommandInfo(name = "clear", totalArguments = 0, commandSyntax = "clear")
public class ClearCommand implements CommandInterface {
    private final FileSystemInterface fileSystemModel;

    public ClearCommand(FileSystemInterface fileSystemModel) {
        this.fileSystemModel = fileSystemModel;
    }

    @Override
    public String execute() {
        return fileSystemModel.clear();
    }
}
