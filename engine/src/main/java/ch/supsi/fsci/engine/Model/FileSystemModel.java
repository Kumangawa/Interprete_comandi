package ch.supsi.fsci.engine.Model;

import java.nio.file.FileSystems;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class FileSystemModel {
    private final DirectoryModel root;
    private final DirectoryModel cur;
    private final String separator = FileSystems.getDefault().getSeparator();

    public FileSystemModel() {
        DirectoryModel root = new DirectoryModel(separator);
        this.root = root;
        this.cur = root;
        add("A");
        add("B");
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
    private DirectoryModel search(final String path){
        if(isAbsolutePath(path)){//caso path assoluto \B\F
            int counter = 0; //serve a scendere nella gerarchia e corrisponde ai livelli della lista orderedPath
            int oldCounter = 0; //serve a controllare se si é trovata la directory specificata
            List<String> orderedPath = (Arrays.stream(path.split(separator + separator)).skip(1).toList());
            DirectoryModel cur_temp = root; //dato che é un absolute path, la prima cartella é la root
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
                    System.out.println("Path non valido");
                    return null;
                }
            }
            System.out.println("Directory di destinazione: " + cur_temp.getName());
            return cur_temp;
        } else {
            return cur;
        }
    }

    public String cd(String path){ // /B/E
        return search(path).getName();
    }

    public String pwd() {
        // Return current working directory
        return "";
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
        return "";
    }

    public void clear() {

    }

    private void add(final String directoryName){
        DirectoryModel newDir = new DirectoryModel(directoryName);
        root.getDir().add(newDir);
    }

    private void add(final DirectoryModel dir){
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