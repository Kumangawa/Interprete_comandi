package ch.supsi.fsci.engine.CommandPattern.Commands;

import ch.supsi.fsci.engine.CommandPattern.CommandInfo;
import ch.supsi.fsci.engine.CommandPattern.CommandInterface;
import ch.supsi.fsci.engine.Model.FileSystemModel;

@CommandInfo(name = "pwd", totalArguments = 0)
public class PwdCommand implements CommandInterface {
    private final FileSystemModel receiver;

    public PwdCommand(final FileSystemModel receiver) {
        this.receiver = receiver;
    }

    @Override
    public String execute() {
        return receiver.pwd();
    }
}
