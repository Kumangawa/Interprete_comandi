package ch.supsi.fsci.engine;

import ch.supsi.fsci.engine.Exceptions.DirectoryNotFound;

import java.nio.file.FileSystems;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class FileSystemModel {
    private final DirectoryModel root;
    private DirectoryModel cur;
    private final String separator = FileSystems.getDefault().getSeparator();
    private Localization localization;

    public FileSystemModel() {
        localization = new Localization();
        DirectoryModel root = new DirectoryModel(separator);
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

    /*
     *Questo metodo restituisce la cartella che dovrebbe ottenere tramite il path specificato
     * toDo: se il path non esiste, segnalarlo e terminare -> Fatto
     * toDo: se il path non esiste, é il caso di ritornare null? È una buona soluzione?
     * toDo: implementare la ricerca per path relativo
     * */
    protected DirectoryModel search(final String path){
        int counter = 0; //serve a scendere nella gerarchia e corrisponde ai livelli della lista orderedPath
        int oldCounter = 0; //serve a controllare se si é trovata la directory specificata
        DirectoryModel cur_temp;
        if(isAbsolutePath(path)){//caso path assoluto \B\F
            List<String> orderedPath = (Arrays.stream(path.split(separator + separator)).skip(1).toList());
            cur_temp = root; //dato che é un absolute path, la prima cartella é la root
            while(counter!=orderedPath.size()){ //quando si arriva alla fine del path corrisponde alla cartella di dest.
                oldCounter = counter;
                for(DirectoryModel dir : cur_temp.getDir()){
                    if(dir.getName().equals(orderedPath.get(counter))){ //al livello corrente si controlla la cartella giusta
                        cur_temp = dir;//quando si trova la cartella giusta si cambia la cur
                        counter++;//scendiamo di un livello di profondità nella gerarchia
                        break;//rompiamo il ciclo
                    }
                }
                if(oldCounter==counter){//se counter non é stato incrementato, significa che non si é trovata la dir e quindi non esiste
                   //dovrà ritornare la cartella cur
                    throw new DirectoryNotFound(String.format(localization.localize("DirectoryNotFound"), orderedPath.get(orderedPath.size()-1), path));
                }
            }
            System.out.println("Directory di destinazione: " + cur_temp.getName());
            return cur_temp;
        } else {
            List<String> orderedRelativePath = (Arrays.stream(path.split(separator + separator)).toList());
            cur_temp = cur;
            while(counter!=orderedRelativePath.size()){
                oldCounter = counter;
                for(DirectoryModel dir : cur_temp.getDir()){
                    if(dir.getName().equals(orderedRelativePath.get(counter))){ //al livello corrente si controlla la cartella giusta
                        cur_temp = dir;//quando si trova la cartella giusta si cambia la cur
                        counter++;//scendiamo di un livello di profondità nella gerarchia
                        break;//rompiamo il ciclo
                    }
                }
                if(oldCounter==counter){//se counter non é stato incrementato, significa che non si é trovata la dir e quindi non esiste
                    //dovrà ritornare la cartella cur
                    throw new DirectoryNotFound(String.format(localization.localize("DirectoryNotFound"), orderedRelativePath.get(orderedRelativePath.size()-1),path));
                }
            }
            System.out.println("Directory di destinazione: " + cur_temp.getName());
            return cur_temp;
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
    public DirectoryModel cd(final String path){ // /B/E
        cur = search(path);
        return cur;
    }

    public String pwd() {
        final StringBuilder stringBuilder = new StringBuilder();
        DirectoryModel currentDirectory = cur;

        while (currentDirectory != null) {
            stringBuilder.insert(0, (currentDirectory != root ? separator : "") + currentDirectory.getName());
            currentDirectory = getParentDirectory(currentDirectory);
        }

        stringBuilder.insert(0, String.format(localization.localize("command.pwd")));
        return stringBuilder.toString();
    }

    private DirectoryModel getParentDirectory(final DirectoryModel directory) {
        if (directory == root) {
            return null;
        }
        for (final DirectoryModel subDir : root.getDir()) {
            if (subDir.getDir().contains(directory)) {
                return subDir;
            }
        }
        return null;
    }

    public String mkdir(final String path) {
        return "";
    }

    public String ls() {
        return "";
    }

    public String mv(final String origin, final String destination) {
        return "";
    }

    public String rm(final String path) {
        return "";
    }

    public String help() {
        return String.format(localization.localize("command.help"));
    }

    public String clear() {
        return "clear";
    }

    // Todo: Remove package-protected, make method private
    // When command to add new sub directories is finished
    // (currently this is used in tests for the equals method, but it shouldn't)
    void add(final String directoryName){
        DirectoryModel newDir = new DirectoryModel(directoryName);
        root.getDir().add(newDir);
    }

    protected void add(final DirectoryModel dir){
        root.getDir().add(dir);
    }

    public DirectoryModel getRoot() {
        return root;
    }

    public DirectoryModel getCur() {
        return cur;
    }

    @Override
    public String toString() {
        String print = "\n";
        for(DirectoryModel dir : root.getDir()){
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
        return Objects.equals(root, that.root) && Objects.equals(cur, that.cur) && Objects.equals(separator, that.separator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(root, cur, separator);
    }
}