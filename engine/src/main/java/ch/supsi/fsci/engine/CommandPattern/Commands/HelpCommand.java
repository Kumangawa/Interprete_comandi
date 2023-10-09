package ch.supsi.fsci.engine.CommandPattern.Commands;

import ch.supsi.fsci.engine.CommandPattern.CommandInfo;
import ch.supsi.fsci.engine.CommandPattern.CommandInterface;
import ch.supsi.fsci.engine.FileSystemModel;

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
