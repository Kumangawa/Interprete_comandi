package ch.supsi.fsci.engine.CommandPattern.Commands;

import ch.supsi.fsci.engine.CommandPattern.CommandInfo;
import ch.supsi.fsci.engine.CommandPattern.CommandInterface;
import ch.supsi.fsci.engine.Interface.FileSystemInterface;

@CommandInfo(name = "help", totalArguments = 0, commandSyntax = "help")
public class HelpCommand implements CommandInterface {
    private final FileSystemInterface receiver;

    public HelpCommand(final FileSystemInterface receiver) {
        this.receiver = receiver;
    }

    @Override
    public String execute() {
        return receiver.help();
    }
}
