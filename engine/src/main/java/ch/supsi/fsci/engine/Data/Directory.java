package ch.supsi.fsci.engine.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Directory {
    private final String name;
    private final List<Directory> dir;

    public Directory(final String name, final List<Directory> dir) {
        this.name = name;
        this.dir = dir;
    }

    public Directory(final String name){
        this.name=name;
        this.dir = new ArrayList<>();
    }

    /*
     * questo modo é momentaneo, serve solo per comodità per fare dei test
     * */

    public void add(final Directory dirToAdd){
        dir.add(dirToAdd);
    }

    public String getName() {
        return name;
    }

    public List<Directory> getDir() {
        return dir;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Directory that = (Directory) o;
        return Objects.equals(name, that.name) && Objects.equals(dir, that.dir);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dir);
    }

}