package ch.supsi.fsci.engine;

import java.nio.file.FileSystems;
import java.util.Arrays;
import java.util.List;

public class FileSystemModel {
    private final DirectoryModel root;
    private final DirectoryModel cur;
    private final String separator = FileSystems.getDefault().getSeparator();

    FileSystemModel() {
        DirectoryModel root = new DirectoryModel(separator);
        this.root = root;
        this.cur = root;
    }

    /*
     * Questo metodo restituisce true se il path é di tipo assoluto,
     * restituisce false se il path é relativo
     * */
    public boolean isAbsolutePath(final String path){
        return path.startsWith(separator);
    }

    /*
     *
     * */
    public DirectoryModel search(final String path){
        //toDo: distinguere se la stringa é un path assoluto(dalla root) => \B\E o relativo (cd E) => E\D\F
        if(isAbsolutePath(path)){//caso path assoluto \B\F
            int counter = 0;
            List<String> orderedPath = (Arrays.stream(path.split(separator + separator)).skip(1).toList());
            DirectoryModel cur = root;
            while(counter!=orderedPath.size()){
                for(DirectoryModel dir : cur.getDir()){
                    if(dir.getName().equals(orderedPath.get(counter))){
                        cur = dir;
                        counter++;
                        break;
                    }
                }
            }
            System.out.println("Directory di destinazione: " + cur.getName());
        }
        return cur;
    }

    public void cd(String path){ // /B/E

    }

    public void add(final String directoryName){
        DirectoryModel newDir = new DirectoryModel(directoryName);
        root.getDir().add(newDir);
    }

    public void add(final DirectoryModel dir){
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
}