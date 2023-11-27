package ch.supsi.fsci.engine.Interface;

import ch.supsi.fsci.engine.Data.Directory;

import java.util.List;

public interface FileSystemInterface {
    Directory cd(String path);
    String pwd();
    String mkdir(String nomeCartella);
    String ls();
    String mv(String origin, String destination);
    String rm(String path);
    String help();
    String clear();
    Directory getRoot();
    Directory getCur();
    void add(final String directoryName);

    void add(final Directory dir);

    String getSeparator();

    Directory search(final String path);
    Directory iterate(Directory startingDirectory, List<String> orderedPath);
    List<String> getOrderedAbsolutePath(final String absolutePath);
    List<String> getOrderedRelativePath(final String relativePath);
}