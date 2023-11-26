package ch.supsi.fsci.engine.Interface;

import ch.supsi.fsci.engine.Model.DirectoryModel;

import java.util.List;

public interface FileSystemInterface {
    DirectoryModel cd(String path);
    String pwd();
    String mkdir(String nomeCartella);
    String ls();
    String mv(String origin, String destination);
    String rm(String path);
    String help();
    String clear();
    DirectoryModel getRoot();
    DirectoryModel getCur();
    void add(final String directoryName);

    void add(final DirectoryModel dir);

    String getSeparator();

    DirectoryModel search(final String path);
    DirectoryModel iterate(DirectoryModel startingDirectory, List<String> orderedPath);
    List<String> getOrderedAbsolutePath(final String absolutePath);
    List<String> getOrderedRelativePath(final String relativePath);
}