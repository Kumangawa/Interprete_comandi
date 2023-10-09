package ch.supsi.fsci.engine.Exceptions;

public class DirectoryNotFound extends RuntimeException{
    public DirectoryNotFound(final String path, final String directoryName){
        super("The directory " + directoryName + " has not been found with the " + path + " path");
    }
}
