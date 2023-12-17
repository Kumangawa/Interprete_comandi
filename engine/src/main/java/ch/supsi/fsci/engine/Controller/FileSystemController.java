package ch.supsi.fsci.engine.Controller;

import ch.supsi.fsci.engine.Interface.FileSystemInterface;
import ch.supsi.fsci.engine.Response;

public class FileSystemController {
    private final FileSystemInterface fileSystemModel;

    public FileSystemController(final FileSystemInterface fileSystemModel) {
        this.fileSystemModel = fileSystemModel;
    }

    public Response cd(final String path) {
        return fileSystemModel.cd(path);
    }

    public Response pwd() {
        return fileSystemModel.pwd();
    }

    public Response mkdir(final String folderName) {
        return fileSystemModel.mkdir(folderName);
    }

    public Response ls() {
        return fileSystemModel.ls();
    }

    public Response mv(final String origin, final String destination) {
        return fileSystemModel.mv(origin, destination);
    }

    public Response rm(final String path) {
        return fileSystemModel.rm(path);
    }

    public Response help() {
        return fileSystemModel.help();
    }
}
