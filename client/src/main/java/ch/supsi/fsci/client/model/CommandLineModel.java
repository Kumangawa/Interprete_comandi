package ch.supsi.fsci.client.model;

import ch.supsi.fsci.engine.FileSystemModel;

import java.lang.reflect.Method;

public class CommandLineModel implements CommandLineModelInterface {
    final FileSystemModel fileSystem;

    public CommandLineModel(final FileSystemModel fileSystem) {
        this.fileSystem = fileSystem;
    }

    public String setText(String text) {
        // 1. Check if given command is valid.
        text = text.trim();
        final String[] commandParts = text.split("\\s+");
        boolean isCommandCorrect = commandParts.length > 0 && checkCommandExistence(commandParts[0]);

        // 2. Dispatch method call based on the given text. Command + factory pattern for this?
        // For now, this is just a "crude" implementation, and we assume that the input is correct.
        if (isCommandCorrect) {
            switch (commandParts[0]) {
                case "pwd": fileSystem.pwd(); return "";
                case "mkdir": fileSystem.mkdir(commandParts[1]); return "";
                case "ls": fileSystem.ls(); return "";
                case "cd": return "changed directory to: " + fileSystem.cd(commandParts[1]);
                case "mv": fileSystem.mv(commandParts[1], commandParts[2]); return "";
                case "rm": fileSystem.rm(commandParts[1]); return "";
                case "help": fileSystem.help(); return "";
                case "clear": fileSystem.clear(); return "";
                default: return "error";
                // handle previously not found errors
            }
        }
        return "error";
    }

    private boolean checkCommandExistence(final String cmd) {
        // getMethods only retrieves public methods (the actual commands)
        for (final Method method: FileSystemModel.class.getMethods()) {
            if (method.getName().equals(cmd)) {
                return true;
            }
        }
        return false;
    }

}
