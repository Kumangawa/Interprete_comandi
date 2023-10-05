package ch.supsi.fsci.engine.Commands;
import ch.supsi.fsci.engine.Model.FileSystemModel;

@CommandInfo(name = "cd", totalArguments = 1)
public class CdCommand implements CommandInterface {
    private final FileSystemModel receiver;
    private final String path;

    public CdCommand(final FileSystemModel fileSystemModel, final String... arguments) {
        this.path = arguments[0];
        this.receiver = fileSystemModel;
    }

    @Override
    public String execute() {
       return receiver.cd(path);
    }
}
