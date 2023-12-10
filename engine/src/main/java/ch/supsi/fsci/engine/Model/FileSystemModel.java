package ch.supsi.fsci.engine.Model;

import ch.supsi.fsci.engine.Data.Directory;
import ch.supsi.fsci.engine.Exceptions.DirectoryNotFound;
import ch.supsi.fsci.engine.Interface.FileSystemInterface;
import ch.supsi.fsci.engine.Response;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class FileSystemModel implements FileSystemInterface {
    private final Directory root;
    private Directory cur;
    private final String separator = "/";

    public FileSystemModel() {
        Directory root = new Directory(separator);
        this.root = root;
        this.cur = root;
    }

    private boolean isAbsolutePath(final String path){
        return path.startsWith(separator);
    }

    private Directory findDirectory(Directory currentDirectory, String dirName) {
        for (Directory dir : currentDirectory.getDir()) {
            if (dir.getName().equals(dirName)) {
                return dir;
            }
        }
        return null;
    }

    private Directory iterate(final Directory startingDirectory, final List<String> orderedPath) {
        Directory curTemp = startingDirectory;
        for (final String dirName : orderedPath) {
            final Directory foundDir = findDirectory(curTemp, dirName);
            if (foundDir == null) {
                throw new DirectoryNotFound("DirectoryNotFound", dirName, orderedPath.toString());
            }
            curTemp = foundDir;
        }
        return curTemp;
    }


    private List<String> getOrderedAbsolutePath(final String absolutePath){
        return (Arrays.stream(absolutePath.split(separator)).skip(1).toList());
    }

    private List<String> getOrderedRelativePath(final String relativePath){
        return (Arrays.stream(relativePath.split(separator)).toList());
    }


    public Directory search(final String path){
        if(isAbsolutePath(path)) {
            List<String> orderedPath = getOrderedAbsolutePath(path);
            return iterate(root, orderedPath);
        } else {
            List<String> orderedRelativePath = getOrderedRelativePath(path);
            return iterate(cur, orderedRelativePath);
        }
    }


    public Response cd(final String path) {
        try {
            cur = search(path);
            return new Response("command.cd.success", cur.getName());
        } catch (final DirectoryNotFound e) {
            return new Response(e.getKey(), e.getAdditionalParameters());
        }
    }
    public Response pwd() {
        final StringBuilder stringBuilder = new StringBuilder("");
        Directory currentDirectory = cur;

        while (currentDirectory != null) {
            stringBuilder.insert(0, (currentDirectory != root ? separator : "") + currentDirectory.getName());
            currentDirectory = getParentDirectory(currentDirectory);
        }

        return new Response("command.pwd", stringBuilder.toString());
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

    public Response mkdir(final String folderName) {
        cur.add(new Directory(folderName));
        return new Response("command.mkdir", folderName);
    }

    public Response ls() {
        final List<Directory> contenutoCur = cur.getDir();
        StringBuilder content = new StringBuilder();
        for (final Directory dir : contenutoCur) {
            content.append(dir.getName()).append(" ");
        }
        return new Response("command.ls.success", content.toString().trim());
    }

    public Response mv(final String origin, final String destination) {
        // TODO: da completare
        try {
            // Cerca la directory di origine
            Directory sourceDir = search(origin);

            // Verifica se il percorso di destinazione è la radice ("/")
            if (destination.equals(separator)) {
                return new Response("command.mv.root");
            }

            // Cerca la directory di destinazione
            Directory destinationDir = search(destination);

            // Verifica se il percorso di destinazione è una sottodirectory della directory di origine
            if (isDescendant(sourceDir, destinationDir)) {
                return new Response("command.mv.failed.descendant");
            }

            // Rimuovi la directory di origine dal suo genitore
            Directory sourceParentDir = getParentDirectory(sourceDir);
            if (sourceParentDir != null) {
                sourceParentDir.getDir().remove(sourceDir);

                // Aggiungi la directory di origine alla directory di destinazione
                destinationDir.getDir().add(sourceDir);
                return new Response("command.mv.success", origin, destination);
            } else {
                return new Response("command.mv.root");
            }
        } catch (DirectoryNotFound e) {
            return new Response(e.getKey(), e.getAdditionalParameters());
        }
    }

    public Response rm(final String path) {
        if (path.equals(separator)) {
            return new Response("command.rm.root", separator);
        }

        try {
            Directory targetDir = search(path);

            if (targetDir == cur || isDescendant(targetDir, cur)) {
                return new Response("command.rm.failed");
            }
            Directory parentDir = getParentDirectory(targetDir);
            if (parentDir != null) {
                parentDir.getDir().remove(targetDir);
            } else {
                Directory d = getRoot();
                d.getDir().remove(targetDir);
            }
            return new Response("command.rm.success", targetDir.getName());

        } catch (DirectoryNotFound e) {
            return new Response(e.getKey(), e.getAdditionalParameters());
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

    public Response help() {
        return new Response("command.help");
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
        StringBuilder print = new StringBuilder("\n");
        for(Directory dir : root.getDir()){
            String currentDir = dir.getName() + "\t";
            print.append(currentDir);
        }
        return print.toString();
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
