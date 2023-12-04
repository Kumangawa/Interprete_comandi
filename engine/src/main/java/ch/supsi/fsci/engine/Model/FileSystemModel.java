package ch.supsi.fsci.engine.Model;

import ch.supsi.fsci.engine.Data.Directory;
import ch.supsi.fsci.engine.Exceptions.DirectoryNotFound;
import ch.supsi.fsci.engine.Interface.FileSystemInterface;
import ch.supsi.fsci.engine.Respons;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class FileSystemModel implements FileSystemInterface {
    private final Directory root;
    private Directory cur;
    private final String separator = "/";

    private final Respons respons;

    public FileSystemModel() {
        respons = new Respons();
        Directory root = new Directory(separator);
        this.root = root;
        this.cur = root;
    }

    /*
     * Questo metodo restituisce true se il path é di tipo assoluto,
     * restituisce false se il path é relativo
     * */
    private boolean isAbsolutePath(final String path){
        return path.startsWith(separator);
    }

    public Directory iterate(Directory startingDirectory, List<String> orderedPath){
        int counter = 0;
        int oldCounter = 0;
        Directory cur_temp = startingDirectory;
        while(counter!=orderedPath.size()){ //quando si arriva alla fine del path corrisponde alla cartella di dest.
            oldCounter = counter;
            for(Directory dir : cur_temp.getDir()){
                if(dir.getName().equals(orderedPath.get(counter))){ //al livello corrente si controlla la cartella giusta
                    cur_temp = dir;//quando si trova la cartella giusta si cambia la cur
                    counter++;//scendiamo di un livello di profondità nella gerarchia
                    break;//rompiamo il ciclo
                }
            }
            if(oldCounter==counter){//se counter non é stato incrementato, significa che non si é trovata la dir e quindi non esiste
                //dovrà ritornare la cartella cur
                throw new DirectoryNotFound(respons.keyAndTwoParameter("DirectoryNotFound",orderedPath.get(orderedPath.size()-1), orderedPath.toString()));
            }
        }
        return cur_temp;
    }

    public List<String> getOrderedAbsolutePath(final String absolutePath){
        return (Arrays.stream(absolutePath.split(separator)).skip(1).toList());
    }

    public List<String> getOrderedRelativePath(final String relativePath){
        return (Arrays.stream(relativePath.split(separator)).toList());
    }

    /*
     *Questo metodo restituisce la cartella che dovrebbe ottenere tramite il path specificato
     * toDo: se il path non esiste, segnalarlo e terminare -> Fatto
     * toDo: se il path non esiste, é il caso di ritornare null? È una buona soluzione?
     * toDo: implementare la ricerca per path relativo
     * */
    public Directory search(final String path){
        if(isAbsolutePath(path)){//caso path assoluto \B\F
            List<String> orderedPath = getOrderedAbsolutePath(path);
            return iterate(root, orderedPath);
        } else {
            List<String> orderedRelativePath = getOrderedRelativePath(path);
            return iterate(cur, orderedRelativePath);
        }
    }

    /* TODO
       - add all commands
         - if the command executes gracefully, return the output
         - else, throw an exception describing the error
           - create new exception classes for custom errors for clarity?
       => all exceptions will then be caught in setText(), and their message will be displayed to the user
     */

    //testare
    public Directory cd(final String path){ // /B/E
        cur = search(path);
        return cur;
    }

    public String pwd() {
        final StringBuilder stringBuilder = new StringBuilder();
        Directory currentDirectory = cur;

        while (currentDirectory != null) {
            stringBuilder.insert(0, (currentDirectory != root ? separator : "") + currentDirectory.getName());
            currentDirectory = getParentDirectory(currentDirectory);
        }

        stringBuilder.insert(0, respons.onlyKey("command.pwd"));
        return stringBuilder.toString();
    }

    private Directory getParentDirectory(final Directory directory) {
        if (directory == root) {
            return null;
        }
        for (final Directory subDir : root.getDir()) {
            if (subDir.getDir().contains(directory)) {
                return subDir;
            }
        }
        return null;
    }

    public String mkdir(final String nomeCartella) {
        cur.add(new Directory(nomeCartella));
        return respons.keyAndOneParameter("command.mkdir",nomeCartella);
    }

    public String ls() {
        List<Directory> contenutoCur = cur.getDir();
        StringBuilder contenuto = new StringBuilder();
        for(Directory dir : contenutoCur){
            contenuto.append(dir.getName()).append(" ");
        }
        return contenuto.toString();
    }

    public String mv(final String origin, final String destination) {
        // TODO: da completare
        try {
            // Cerca la directory di origine
            Directory sourceDir = search(origin);

            // Verifica se il percorso di destinazione è la radice ("/")
            if (destination.equals(separator)) {
                return respons.onlyKey("command.mv.root");
            }

            // Cerca la directory di destinazione
            Directory destinationDir = search(destination);

            // Verifica se il percorso di destinazione è una sottodirectory della directory di origine
            if (isDescendant(sourceDir, destinationDir)) {
                return respons.onlyKey("command.mv.failed.descendant");
            }

            // Rimuovi la directory di origine dal suo genitore
            Directory sourceParentDir = getParentDirectory(sourceDir);
            if (sourceParentDir != null) {
                sourceParentDir.getDir().remove(sourceDir);

                // Aggiungi la directory di origine alla directory di destinazione
                destinationDir.getDir().add(sourceDir);
                return respons.keyAndTwoParameter("command.mv.success",sourceDir.getName(), destination);
            } else {
                return respons.onlyKey("command.mv.root");
            }
        } catch (DirectoryNotFound e) {
            return e.getMessage();
        }
    }

    public String rm(final String path) {
        if (path.equals(separator)) {
            return respons.keyAndOneParameter("command.rm.root", separator);
        }

        try {
            Directory targetDir = search(path);

            if (targetDir == cur || isDescendant(targetDir, cur)) {
                return respons.onlyKey("command.rm.failed");
            }
            Directory parentDir = getParentDirectory(targetDir);
            if (parentDir != null) {
                parentDir.getDir().remove(targetDir);
            } else {
                Directory d = getRoot();
                d.getDir().remove(targetDir);
            }
            return respons.keyAndOneParameter("command.rm.success", targetDir.getName());

        } catch (DirectoryNotFound e) {
            return e.getMessage();
        }
    }

    private boolean isDescendant(Directory ancestor, Directory descendant) {
        while (descendant != null) {
            if (descendant == ancestor) {
                return true;
            }
            descendant = getParentDirectory(descendant);
        }
        return false;
    }

    public String help() {
        return respons.onlyKey("command.help");
    }

    public String clear() {
        return "clear";
    }

    // Todo: Make method private
    // When command to add new sub directories is finished
    // (currently this is used in tests for the equals method, but it shouldn't)
    public void add(final String directoryName){
        Directory newDir = new Directory(directoryName);
        root.getDir().add(newDir);
    }

    public void add(final Directory dir){
        root.getDir().add(dir);
    }

    public Directory getRoot() {
        return root;
    }

    public Directory getCur() {
        return cur;
    }

    @Override
    public String toString() {
        String print = "\n";
        for(Directory dir : root.getDir()){
            String currentDir = dir.getName() + "\t";
            print = print+currentDir;
        }
        return print;
    }

    public String getSeparator() {
        return separator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileSystemModel that = (FileSystemModel) o;
        return Objects.equals(root, that.root) && Objects.equals(cur, that.cur);
    }

    @Override
    public int hashCode() {
        return Objects.hash(root, cur, separator);
    }
}