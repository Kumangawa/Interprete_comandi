package ch.supsi.fsci.engine.CommandPattern.Commands;

import ch.supsi.fsci.engine.CommandPattern.CommandInfo;
import ch.supsi.fsci.engine.CommandPattern.CommandInterface;
import ch.supsi.fsci.engine.Controller.FileSystemController;
import ch.supsi.fsci.engine.Interface.FileSystemInterface;

@CommandInfo(name = "clear", totalArguments = 0, commandSyntax = "clear")
public class ClearCommand implements CommandInterface {
    private final FileSystemController fileSystemModel;

    public ClearCommand(FileSystemController fileSystemController) {

        this.fileSystemModel = fileSystemController;
    }

    @Override
    public String execute() {
        return fileSystemModel.clear();
    }
}
