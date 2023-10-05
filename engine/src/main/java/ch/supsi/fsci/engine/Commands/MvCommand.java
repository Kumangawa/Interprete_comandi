package ch.supsi.fsci.engine.Commands;

import ch.supsi.fsci.engine.Model.FileSystemModel;

@CommandInfo(name = "mv", totalArguments = 2)
public class MvCommand implements CommandInterface {
    private final FileSystemModel receiver;
    private final String origin;
    private final String destination;

    public MvCommand(final FileSystemModel receiver, final String... arguments) {
        this.receiver = receiver;
        this.origin = arguments[0];
        this.destination = arguments[1];
    }

    @Override
    public String execute() {
        return receiver.mv(origin, destination);
    }
}
