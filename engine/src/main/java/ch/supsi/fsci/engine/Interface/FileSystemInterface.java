package ch.supsi.fsci.engine.Interface;

import ch.supsi.fsci.engine.Data.Directory;
import ch.supsi.fsci.engine.Response;

public interface FileSystemInterface {
    Response cd(String path);
    Response pwd();
    Response mkdir(String folderName);
    Response ls();
    Response mv(String origin, String destination);
    Response rm(String path);
    Response help();
    Directory getRoot();
    Directory getCur();
    void add(final Directory dir);
    String getSeparator();
    Directory search(final String path);
}