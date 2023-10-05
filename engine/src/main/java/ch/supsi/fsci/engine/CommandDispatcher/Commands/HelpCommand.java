package ch.supsi.fsci.engine.CommandDispatcher.Commands;

import ch.supsi.fsci.engine.CommandDispatcher.CommandInfo;
import ch.supsi.fsci.engine.CommandDispatcher.CommandInterface;
import ch.supsi.fsci.engine.Model.FileSystemModel;

@CommandInfo(name = "help", totalArguments = 0)
public class HelpCommand implements CommandInterface {
    private final FileSystemModel receiver;

    public HelpCommand(final FileSystemModel receiver) {
        this.receiver = receiver;
    }

    @Override
    public String execute() {
        return receiver.help();
    }
}
