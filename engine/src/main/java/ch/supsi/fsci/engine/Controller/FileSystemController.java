package ch.supsi.fsci.engine.Controller;

import ch.supsi.fsci.engine.Model.DirectoryModel;
import ch.supsi.fsci.engine.Interface.FileSystemInterface;

public class FileSystemController {
    private final FileSystemInterface fileSystemModel;

    public FileSystemController(final FileSystemInterface fileSystemModel) {
        this.fileSystemModel = fileSystemModel;
    }

    public DirectoryModel cd(final String path) {
        return fileSystemModel.cd(path);
    }

    public String pwd() {
        return fileSystemModel.pwd();
    }

    public String mkdir(final String nomeCartella) {
        return fileSystemModel.mkdir(nomeCartella);
    }

    public String ls() {
        return fileSystemModel.ls();
    }

    public String mv(final String origin, final String destination) {
        return fileSystemModel.mv(origin, destination);
    }

    public String rm(final String path) {
        return fileSystemModel.rm(path);
    }

    public String help() {
        return fileSystemModel.help();
    }

    public String clear() {
        return fileSystemModel.clear();
    }
}
