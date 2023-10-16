package ch.supsi.fsci.engine.Exceptions;

public class DirectoryNotFound extends RuntimeException{
    public DirectoryNotFound(final String message){
        super(message);
    }
}
