package ch.supsi.fsci.engine.CommandPattern.Commands;

import ch.supsi.fsci.engine.CommandPattern.CommandInfo;
import ch.supsi.fsci.engine.CommandPattern.CommandInterface;
import ch.supsi.fsci.engine.Interface.FileSystemInterface;

@CommandInfo(name = "pwd", totalArguments = 0, commandSyntax = "pwd")
public class PwdCommand implements CommandInterface {
    private final FileSystemInterface receiver;

    public PwdCommand(final FileSystemInterface receiver) {
        this.receiver = receiver;
    }

    @Override
    public String execute() {
        return receiver.pwd();
    }
}
